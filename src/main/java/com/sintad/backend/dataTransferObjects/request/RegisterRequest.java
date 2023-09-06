package com.sintad.backend.dataTransferObjects.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {
    private String username;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> rol;
}
