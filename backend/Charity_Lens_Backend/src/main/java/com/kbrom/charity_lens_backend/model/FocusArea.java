package com.kbrom.charity_lens_backend.model;

import jakarta.persistence.*;

@Entity
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

}
