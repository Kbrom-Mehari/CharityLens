package com.kbrom.charity_lens_backend.auth.dto;

import com.kbrom.charity_lens_backend.user.enums.Role;

import java.util.Set;

public record UserSecurityDTO(
        Long id,
        String username,
        String email,
        Set<Role> roles,
        boolean enabled)
{ }
