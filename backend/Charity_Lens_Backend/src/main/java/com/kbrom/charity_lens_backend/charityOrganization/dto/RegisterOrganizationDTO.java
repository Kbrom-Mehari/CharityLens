package com.kbrom.charity_lens_backend.charityOrganization.dto;

import com.kbrom.charity_lens_backend.focusArea.FocusArea;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RegisterOrganizationDTO {
    private String name;
    private String email;
    private String phone;
    private String password;
    private List<FocusArea> focusAreas;
}
