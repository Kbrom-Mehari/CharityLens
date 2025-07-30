package com.kbrom.charity_lens_backend.dto;

import com.kbrom.charity_lens_backend.model.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class DonationDTO {
    private Long donationId;
    private String donorFirstName;
    private String donorLastName;
    private Long projectId;
    private Long campaignId;
    private BigDecimal amount;
    private Currency currency;
    private LocalDateTime createdAt;
}
