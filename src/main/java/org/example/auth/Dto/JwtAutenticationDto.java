package org.example.auth.Dto;
import  lombok.Data;

@Data
public class JwtAutenticationDto {
    private  String token;
    private  String refreshToken;
}
