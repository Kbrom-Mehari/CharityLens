package com.kbrom.charity_lens_backend.dto;

import com.kbrom.charity_lens_backend.model.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DonateToProjectDTO {
    private Long projectId;
    private Long userId;
    private BigDecimal amount;
    private Currency currency;


}
