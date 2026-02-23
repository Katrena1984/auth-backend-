package org.example.auth.service;

import org.example.auth.Dto.UserCredentialsDto;
import org.example.auth.Dto.RefreshTokenDto;
import org.example.auth.Dto.JwtAutenticationDto;
import org.example.auth.Dto.UserDto;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface UserService {
    JwtAutenticationDto signIn(UserCredentialsDto userCredentialsDto);
    JwtAutenticationDto refreshToken(RefreshTokenDto refreshTokenDto);
    UserDto getUserById(String id) throws  ChangeSetPersister.NotFoundException;
    UserDto getUserByEmail(String email) throws ChangeSetPersister.NotFoundException;
    String addUser(UserDto user);
}
