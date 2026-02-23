package org.example.auth.mapper;
import org.example.auth.Dto.UserDto;
import org.example.auth.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // будет хешироваться позже
        user.setRole(User.Role.valueOf(dto.getRole().toUpperCase()));
        return user;
    }

    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole().name());
        return dto;
    }
}