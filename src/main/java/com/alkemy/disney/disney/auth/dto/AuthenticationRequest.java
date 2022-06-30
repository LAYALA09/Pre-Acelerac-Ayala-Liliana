package com.alkemy.disney.disney.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationRequest {
    @Size(message = "username must be between 3 and 20 characters", min = 3, max = 20)
    @NotBlank(message = "username is required")
    private String username;
    @Size(message = "password must be at least 8 characters long", min = 8)
    @NotBlank(message = "password is required")
    private String password;
}
