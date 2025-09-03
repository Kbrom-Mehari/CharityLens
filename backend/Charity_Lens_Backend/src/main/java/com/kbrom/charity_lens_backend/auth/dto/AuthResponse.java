package com.kbrom.charity_lens_backend.auth.dto;

import com.kbrom.charity_lens_backend.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String token;
    private String username;
    private Role role;

}
