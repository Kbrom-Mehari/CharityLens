package com.kbrom.charity_lens_backend.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDonorProfileDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
