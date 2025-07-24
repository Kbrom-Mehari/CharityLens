package com.kbrom.charity_lens_backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CharityOrganization {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private  String name;
    @Column(nullable = false,unique = true)
    private  String email;
    @Column(nullable = false)
    private  String phone;
    @OneToMany
    private List<Project> projects=new ArrayList<>();


}
