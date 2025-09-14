package com.kbrom.charity_lens_backend.auth.dto;

import com.kbrom.charity_lens_backend.focusArea.FocusArea;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RegisterCharityOrganizationDTO {
    @NotBlank(message = "organization name is required")
    private String organizationName;

    private String phoneNumber;
    @Pattern(regexp="^(?!.*[._])[a-zA-Z0-9](?:[a-zA-Z0-9._]{1,18}[a-zA-Z0-9])?$"
            ,message = "username cannot have special characters, must be 3-20 characters,start/end with letter/number,cannot have consecutive . or _")
    @NotBlank(message = "username is required")
    private String username;
    @Email(message = "enter a valid email address")
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min=8,max=20,message = "password must be between 8-20")
    private String password;
    private List<FocusArea> focusAreas= new ArrayList<>();

}
