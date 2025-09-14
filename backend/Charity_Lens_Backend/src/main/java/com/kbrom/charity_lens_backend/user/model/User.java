package com.kbrom.charity_lens_backend.user.model;

import com.kbrom.charity_lens_backend.charityOrganization.model.CharityOrganization;
import com.kbrom.charity_lens_backend.user.enums.Gender;
import com.kbrom.charity_lens_backend.user.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false,unique = true)
    private String username;

    private String firstName;

    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private List<Role> roles=new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private boolean isFlagged=false;
    private boolean isEnabled=false;
    @OneToOne(mappedBy = "user")
    private CharityOrganization organizationProfile;
}