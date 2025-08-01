package com.kbrom.charity_lens_backend.dto;

import com.kbrom.charity_lens_backend.model.enums.Cause;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateCampaignDTO {
    private String title;
    private String objective;
    private String description;
    private BigDecimal donationGoal;
    private LocalDate endDate;
    private Cause cause;
}
