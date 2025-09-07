package com.kbrom.charity_lens_backend.auth.security;


import com.kbrom.charity_lens_backend.user.enums.Role;
import com.kbrom.charity_lens_backend.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CustomUserDetails implements UserDetails {
    private final User user;
    private CustomUserDetails(User user) {
        this.user = user;
    }
    public static CustomUserDetails from(User user) {
        return new CustomUserDetails(user);
    }
    public record UserSecurityDTO(Long id, String username, String email, Role role,boolean enabled) {}
    public UserSecurityDTO getDomainUser(){
        return new UserSecurityDTO(user.getId(),user.getUsername(),user.getEmail(),user.getRole(),user.isEnabled());
    }
    public Long getId(){
        return user.getId();
    }
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isEnabled(){
        return user.isEnabled();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole().name()));
    }
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if(!(o instanceof CustomUserDetails that)) return false;
        if(this.getId()!=null&&that.getId()!=null){
            return Objects.equals(this.getId(),that.getId());
        }
        return Objects.equals(this.getUsername(),that.getUsername());
    }
    @Override
    public int hashCode() {
        return (this.getId()!=null)
                ? this.getId().hashCode()
                : Objects.hashCode(this.getUsername());
    }
    @Override
    public String toString() {
        return "CustomUserDetails{username=%s,id=%s,enabled=%s}"
                .formatted(this.getUsername(),this.getId(),this.isEnabled());
    }
}
