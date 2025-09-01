package com.kbrom.charity_lens_backend.project.dto;

import com.kbrom.charity_lens_backend.common.enums.Cause;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GetProjectDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal donationGoal;
    private BigDecimal totalDonation;
    private Cause cause;
}
