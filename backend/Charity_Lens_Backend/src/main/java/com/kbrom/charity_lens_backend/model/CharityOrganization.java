package com.kbrom.charity_lens_backend.model;

import jakarta.persistence.*;

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


}
