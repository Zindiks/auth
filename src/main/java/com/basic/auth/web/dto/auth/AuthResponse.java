package com.basic.auth.web.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthResponse {

    private Long id;
    private String username;
    private String email;
    private String token;


}
