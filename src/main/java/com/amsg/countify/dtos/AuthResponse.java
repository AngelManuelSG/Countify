package com.amsg.countify.dtos;

public record AuthResponse(
        String token,
        String type
) {
    public AuthResponse(String token){
        this(token, "Bearer");
    }
}
