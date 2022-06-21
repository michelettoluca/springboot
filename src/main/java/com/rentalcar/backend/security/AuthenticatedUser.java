package com.rentalcar.backend.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class AuthenticatedUser implements UserDetails {
    private final Integer id;
    private final String username;
    private final Set<GrantedAuthority> authorities;
    private final String password;

    public AuthenticatedUser(
            Integer id,
            String username,
            String password,
            String[] roles) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = Arrays
                .stream(roles)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    public boolean isAdmin() {
        return this.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public boolean isCustomer() {
        return this.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return true;
    }
}
