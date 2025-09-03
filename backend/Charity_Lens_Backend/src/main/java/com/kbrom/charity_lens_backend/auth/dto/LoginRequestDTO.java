package com.kbrom.charity_lens_backend.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    @NotBlank(message = "email/username is required")
    private String login;
    @NotBlank(message = "password is required")
    @Size(min = 8,max=20,message = "password must be 8-20 characters")
    private String password;
}
