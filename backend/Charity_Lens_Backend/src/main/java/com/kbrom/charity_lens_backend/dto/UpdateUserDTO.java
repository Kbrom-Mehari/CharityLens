package com.kbrom.charity_lens_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
