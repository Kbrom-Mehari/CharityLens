package com.kbrom.charity_lens_backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(nullable = false)
    private  String name;
    @ManyToOne    // an organization can have many projects
    private CharityOrganization organization;
    private boolean isActive;
    private BigDecimal donationGoal;
    @OneToMany(mappedBy="project")
    private List<Donation> donations=new ArrayList<>();

}
