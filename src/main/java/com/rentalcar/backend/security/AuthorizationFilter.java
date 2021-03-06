package com.rentalcar.backend.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.rentalcar.backend.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (!request.getServletPath().equals("/auth/login")) {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String token = authorizationHeader.substring("Bearer ".length());

                    DecodedJWT decodedJWT = this.jwtUtils.decode(token);

                    String username = decodedJWT.getSubject();
                    Integer id = decodedJWT.getClaim("id").asInt();

                    Collection<SimpleGrantedAuthority> authorities = decodedJWT
                            .getClaim("roles")
                            .asList(String.class)
                            .stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authenticationToken);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
