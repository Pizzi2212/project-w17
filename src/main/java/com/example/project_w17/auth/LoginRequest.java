package com.example.project_w17.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
