package com.kbrom.charity_lens_backend.model;

import com.kbrom.charity_lens_backend.model.enums.Gender;
import com.kbrom.charity_lens_backend.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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
    @Email
    private String email;
    @Size(min=8,max=20,message = "password must be between 8-20")
    private String password;
    private Role role;
    private Gender gender;

}