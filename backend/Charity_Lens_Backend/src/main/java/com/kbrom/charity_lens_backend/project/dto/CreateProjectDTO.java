package com.kbrom.charity_lens_backend.project.dto;

import com.kbrom.charity_lens_backend.common.enums.Cause;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProjectDTO {            // for creating and updating projects
    private String title;
    private String description;
    private Cause cause;
    private Long organizationId;
    private BigDecimal donationGoal;
}
