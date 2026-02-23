package org.example.auth.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.example.auth.Dto.JwtAutenticationDto;
import org.example.auth.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.auth.repository.UserRepository;
import org.example.auth.mapper.UserMapper;
import org.example.auth.Dto.UserDto;
import org.example.auth.Dto.RefreshTokenDto;
import org.example.auth.Dto.UserCredentialsDto;
import org.example.auth.entity.User;
import org.example.auth.security.jwt.JwtSer;
import java.util.Optional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class userServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtSer jwtSer;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtAutenticationDto signIn(UserCredentialsDto userCredentialsDto) {
        User user = findByCredentials(userCredentialsDto);
        return jwtSer.generateAuthToken(user.getEmail());
    }

    @Override
    public JwtAutenticationDto refreshToken(RefreshTokenDto refreshTokenDto) {
        String refreshToken = refreshTokenDto.getRefreshToken();
        if (refreshToken != null && jwtSer.validateJwtToken(refreshToken)){
            User user = findByEmail(jwtSer.getEmailFromToken(refreshToken));
            return jwtSer.refreshBaseToken(user.getEmail(), refreshToken);
        }
        throw new BadCredentialsException("Invalid refresh token");
    }

    @Override
    @Transactional
    public UserDto getUserById(String id) throws ChangeSetPersister.NotFoundException{
        return  userMapper.toDto(userRepository.findById(UUID.fromString(id))
                .orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    @Transactional
    public UserDto getUserByEmail(String email) throws ChangeSetPersister.NotFoundException{
        return  userMapper.toDto(userRepository.findByEmail(email)
                .orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public  String addUser(UserDto userDto){
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return  "User added";
    }
    private User findByCredentials(UserCredentialsDto userCredentialsDto){
        Optional<User> optionalUser = userRepository.findByEmail(userCredentialsDto.getEmail());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (passwordEncoder.matches(userCredentialsDto.getPassword(), user.getPassword())){
                return user;
            }
        }
        throw new BadCredentialsException("Email or password is not correct");
    }
    private  User findByEmail(String email){
        return  userRepository.findByEmail(email).orElseThrow(() -> new BadCredentialsException("User with email " + email + " is not found"));

    }
}
