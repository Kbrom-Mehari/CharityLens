package com.kbrom.charity_lens_backend.auth.security;


import com.kbrom.charity_lens_backend.auth.dto.UserSecurityDTO;
import com.kbrom.charity_lens_backend.user.enums.Role;
import com.kbrom.charity_lens_backend.user.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CustomUserDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String email;
    private final String password;
    private final boolean isEnabled;
    private final Set<Role> roles;

    private CustomUserDetails(Long id, String username, String email, String password, boolean isEnabled, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public static CustomUserDetails from(User user) {
        return new CustomUserDetails(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.getRoles()==null? Set.of():Set.copyOf(user.getRoles()));
    }
    public UserSecurityDTO getDomainUser(){
        return new UserSecurityDTO(id,username,email,roles,isEnabled);
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isEnabled(){
        return isEnabled;
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
       return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet());
    }
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if(!(o instanceof CustomUserDetails that)) return false;
        return Objects.equals(this.getId(),that.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    @Override
    public String toString() {
        return "CustomUserDetails{username=%s,id=%s,enabled=%s}"
                .formatted(username,id,isEnabled);
    }
}
