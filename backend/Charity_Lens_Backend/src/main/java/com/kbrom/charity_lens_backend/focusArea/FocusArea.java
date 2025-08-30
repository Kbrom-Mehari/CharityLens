package com.kbrom.charity_lens_backend.focusArea;

import com.kbrom.charity_lens_backend.charityOrganization.model.CharityOrganization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FocusArea {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String sdgGoal;
    @ManyToMany(mappedBy="focusAreas")
    private List<CharityOrganization> charityOrganizations=new ArrayList<>();

public FocusArea(String name, String description, String sdgGoal) {
    this.name = name;
    this.description = description;
    this.sdgGoal = sdgGoal;
}
}
