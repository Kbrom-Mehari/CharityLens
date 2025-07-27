package com.kbrom.charity_lens_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
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
    private boolean isFlagged=false;


}
