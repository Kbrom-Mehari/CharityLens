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
    @Column(nullable = false,unique = true)
    private  String name;
    @Column(nullable = false)
    private  String phone;
    @Column(nullable = false,unique = true)
    private  String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy="organization")
    private List<Project> projects=new ArrayList<>();
    private boolean isFlagged=false;
    @ManyToMany
    @JoinTable(
        name="charity_organization_focus_areas",
        joinColumns=@JoinColumn(name="organization_id"),
        inverseJoinColumns=@JoinColumn(name="focus_area_id")
    )
    private List<FocusArea> focusAreas=new ArrayList<>();


}
