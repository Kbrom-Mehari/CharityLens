package com.kbrom.charity_lens_backend.user.dto;

import com.kbrom.charity_lens_backend.user.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    private String email;
    private String password;
    private String firstName;
    private  String lastName;
    private String username;
    private Gender gender;
}
