package com.sintad.backend.dataTransferObjects.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JWTResponse {
    private String token;

    private String type="Bearer";

    private String username;
    private String email;
    
    private List<String> roles;

    public JWTResponse(String jwt, String username, String email, List<String> roles) {
        this.token = jwt;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
