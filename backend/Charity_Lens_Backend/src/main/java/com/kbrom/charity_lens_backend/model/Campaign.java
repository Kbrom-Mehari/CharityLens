package com.kbrom.charity_lens_backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy="campaign")
    private List<Donation> donations=new ArrayList<>();
}
