package com.kbrom.charity_lens_backend.dto;

import com.kbrom.charity_lens_backend.model.FocusArea;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
public class GetOrganizationDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<FocusArea> focusAreas;
    private boolean isFlagged;

}
