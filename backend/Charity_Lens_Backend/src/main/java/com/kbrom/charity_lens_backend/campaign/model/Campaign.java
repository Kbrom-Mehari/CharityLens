package com.kbrom.charity_lens_backend.campaign.model;

import com.kbrom.charity_lens_backend.donation.model.Donation;
import com.kbrom.charity_lens_backend.common.enums.Cause;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String objective;
    private String description;
    private BigDecimal donationGoal;
    private LocalDate startDate=LocalDate.now();
    private LocalDate endDate;
    @OneToMany(mappedBy="campaign")
    private List<Donation> donations=new ArrayList<>();
    private BigDecimal totalDonation=BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private Cause cause;
}
