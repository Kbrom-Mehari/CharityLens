package com.kbrom.charity_lens_backend.auth.dto;

import com.kbrom.charity_lens_backend.user.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDonorDTO {
    @NotBlank(message = "username is required")
    @Pattern(regexp="^(?!.*[._])[a-zA-Z0-9](?:[a-zA-Z0-9._]{1,18}[a-zA-Z0-9])?$"
            ,message = "username cannot have special characters, must be 3-20 characters,start/end with letter/number,cannot have consecutive . or _")
    private String username;
    @NotBlank(message = "email is required")
    @Email(message = "email address must be valid")
    private String email;
    @Size(min=8, max=20,message = "password must be 8-20 characters")
    private String password;
    private String firstName;
    private String lastName;
    @NotNull(message = "gender is required")
    private Gender gender;
    private String phoneNumber;

}
