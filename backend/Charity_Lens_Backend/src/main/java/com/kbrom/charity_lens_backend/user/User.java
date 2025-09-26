package com.kbrom.charity_lens_backend.user;

import com.kbrom.charity_lens_backend.user.charityOrganization.CharityOrganization;
import com.kbrom.charity_lens_backend.user.donorProfile.DonorProfile;
import com.kbrom.charity_lens_backend.user.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.*;

@Setter
@Getter
@Entity
@Check(constraints = "(organization_profile_id IS NULL OR donor_profile_id IS NULL)") //enforce a user cannot both profiles
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false,unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="user_roles",
            joinColumns=@JoinColumn(name="user_id")
    )
    private Set<Role> roles=EnumSet.noneOf(Role.class);
    private boolean isFlagged=false;
    private boolean isEnabled=false;
    @OneToOne(mappedBy = "user")
    @JoinColumn(name="organization_profile_id")
    private CharityOrganization organizationProfile;
    @OneToOne(mappedBy = "user")
    @JoinColumn(name="donor_profile_id")
    private DonorProfile profile;
}