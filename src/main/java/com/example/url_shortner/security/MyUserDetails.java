package com.example.url_shortner.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final boolean isEnabled;
    private final String[] roles;

    public MyUserDetails(Long id, String username, String password, boolean isEnabled, String... roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(roles)
                .map(s -> "ROLE_" + s)
                .map(s -> (GrantedAuthority) () -> s)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isEnabled;
    }
}
