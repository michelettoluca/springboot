package com.rentalcar.backend.security;

import com.rentalcar.backend.dto.AuthenticatedUser;
import com.rentalcar.backend.type.UserRole;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@ToString
public class AuthenticatedUserToken extends AbstractAuthenticationToken {
    private final Integer id;

    public AuthenticatedUserToken(Integer id, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.id = id;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return id;
    }

    @Override
    public Object getPrincipal() {
        return new AuthenticatedUser(
                this.id,
                UserRole.valueOf(this.getAuthorities().toArray()[0].toString())
        );
    }
}
