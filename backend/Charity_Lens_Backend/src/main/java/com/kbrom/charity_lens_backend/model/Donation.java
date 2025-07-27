package com.kbrom.charity_lens_backend.model;

import com.kbrom.charity_lens_backend.model.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne    //Many donations can be made to a project
    private Project project;
    @ManyToOne   //Many donations can be made for a campaign
    private Campaign campaign;
    @ManyToOne    // A user can make many donations
    private User donor;
    @Column(precision=10, scale = 2,nullable = false)
    private BigDecimal donationAmount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

}
