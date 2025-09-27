package com.kbrom.charity_lens_backend.user.charityOrganization;

import com.kbrom.charity_lens_backend.campaign.model.Campaign;
import com.kbrom.charity_lens_backend.focusArea.FocusArea;
import com.kbrom.charity_lens_backend.project.model.Project;
import com.kbrom.charity_lens_backend.user.User;
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
    private  String organizationName;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;
    private String website;

    @OneToMany(mappedBy="organization",fetch = FetchType.LAZY)
    private List<Project> projects=new ArrayList<>();
    @OneToMany(mappedBy = "organization",fetch = FetchType.LAZY)
    private List<Campaign> campaigns=new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name="charity_organization_focus_areas",
        joinColumns=@JoinColumn(name="organization_id"),
        inverseJoinColumns=@JoinColumn(name="focus_area_id")
    )
    private List<FocusArea> focusAreas=new ArrayList<>();
}
