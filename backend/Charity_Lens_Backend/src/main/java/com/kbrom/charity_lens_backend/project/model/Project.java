package com.kbrom.charity_lens_backend.project.model;

import com.kbrom.charity_lens_backend.charityOrganization.model.CharityOrganization;
import com.kbrom.charity_lens_backend.donation.model.Donation;
import com.kbrom.charity_lens_backend.common.enums.Cause;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(nullable = false)
    private  String title;
    private  String description;
    @ManyToOne    // an organization can have many projects
    @JoinColumn(name="organization_id")
    private CharityOrganization organization;
    private boolean isActive=true;
    private BigDecimal donationGoal;
    @OneToMany(mappedBy="project")
    private List<Donation> donations=new ArrayList<>();
    private BigDecimal totalDonation=BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private Cause cause;

}
