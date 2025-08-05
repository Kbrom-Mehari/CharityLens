package com.kbrom.charity_lens_backend.model;

import com.kbrom.charity_lens_backend.model.enums.Gender;
import com.kbrom.charity_lens_backend.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false,unique = true)
    private String username;
    @Column(nullable=false)
    private String firstName;
    @Column(nullable=false)
    private String lastName;
    @Column(nullable = false,unique = true)
    @Email(message="Enter a valid email address")
    private String email;
    @Size(min=8,max=20,message = "password must be between 8-20")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    private boolean isFlagged=false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(()->"ROLE_"+role.name());
    }
    @Override public boolean isAccountNonExpired() {return true;}
    @Override public boolean isAccountNonLocked() {return true;}
    @Override public boolean isCredentialsNonExpired() {return true;}
    @Override public boolean isEnabled() {return true;}
    @Override public String getPassword() {return this.password;}
    @Override public String getUsername() {return username;}

}