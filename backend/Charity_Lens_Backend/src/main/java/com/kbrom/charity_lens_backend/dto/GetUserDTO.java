package com.kbrom.charity_lens_backend.dto;

import com.kbrom.charity_lens_backend.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserDTO {
    private Long id;
    private String firstName;
    private  String lastName;
    private String email;
    private Role role;
}
