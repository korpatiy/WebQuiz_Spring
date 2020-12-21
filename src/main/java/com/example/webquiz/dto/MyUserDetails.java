package com.example.webquiz.dto;

import com.example.webquiz.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private String username;
    private String email;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.username = "user";
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
    }

    @Override
    public List<GrantedAuthority> getAuthorities(){
        authorities = new ArrayList<>();
       // authorities.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
