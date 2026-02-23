package org.example.auth.Dto;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    private String role; // ADMIN, EDITOR, VIEWER
}