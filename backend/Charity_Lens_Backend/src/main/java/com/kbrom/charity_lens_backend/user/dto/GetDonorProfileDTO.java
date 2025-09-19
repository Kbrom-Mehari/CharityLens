package com.kbrom.charity_lens_backend.user.dto;

import com.kbrom.charity_lens_backend.user.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GetDonorProfileDTO {
    private Long id;
    private String firstName;
    private  String lastName;
    private String email;
    private Set<Role> role;
}
