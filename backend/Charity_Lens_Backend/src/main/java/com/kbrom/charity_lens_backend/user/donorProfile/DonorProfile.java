package com.kbrom.charity_lens_backend.user.donorProfile;

import com.kbrom.charity_lens_backend.user.enums.Gender;
import com.kbrom.charity_lens_backend.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DonorProfile {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;
}
