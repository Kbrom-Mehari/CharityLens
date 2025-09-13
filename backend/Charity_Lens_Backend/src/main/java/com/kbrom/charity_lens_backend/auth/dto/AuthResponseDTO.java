package com.kbrom.charity_lens_backend.auth.dto;

import com.kbrom.charity_lens_backend.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponseDTO {
    private String token;
    private Date expiresAt=new Date();
    private String username;
    private List<String> roles=new ArrayList<>();

}
