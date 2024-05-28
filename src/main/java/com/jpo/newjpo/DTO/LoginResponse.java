package com.jpo.newjpo.DTO;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
}
