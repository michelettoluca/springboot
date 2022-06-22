package com.rentalcar.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final AuthorizationFilter authorizationFilter;

    //    Users endpoints
    @Value("${security.endpoints.users.post}")
    private String[] usersEndpointsPOST;
    @Value("${security.endpoints.users.get}")
    private String[] usersEndpointsGET;
    @Value("${security.endpoints.users.put}")
    private String[] usersEndpointsPUT;
    @Value("${security.endpoints.users.delete}")
    private String[] usersEndpointsDELETE;

    //    Vehicles endpoints
    @Value("${security.endpoints.vehicles.post}")
    private String[] vehiclesEndpointsPOST;
    @Value("${security.endpoints.vehicles.get}")
    private String[] vehiclesEndpointsGET;
    @Value("${security.endpoints.vehicles.put}")
    private String[] vehiclesEndpointsPUT;
    @Value("${security.endpoints.vehicles.delete}")
    private String[] vehiclesEndpointsDELETE;

    //    Reservations endpoints
    @Value("${security.endpoints.reservations.post}")
    private String[] reservationsEndpointsPOST;
    @Value("${security.endpoints.reservations.get}")
    private String[] reservationsEndpointsGET;
    @Value("${security.endpoints.reservations.put}")
    private String[] reservationsEndpointsPUT;
    @Value("${security.endpoints.reservations.delete}")
    private String[] reservationsEndpointsDELETE;

    @Value("${security.allowed-origins}")
    private List<String> allowedOrigins;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()

                .and()

                .authorizeRequests()

                // Users endpoint
                .antMatchers(HttpMethod.POST, usersEndpointsPOST).permitAll()
                .antMatchers(HttpMethod.GET, usersEndpointsGET).authenticated()
                .antMatchers(HttpMethod.PUT, usersEndpointsPUT).authenticated()
                .antMatchers(HttpMethod.DELETE, usersEndpointsDELETE).authenticated()

                // Vehicles endpoint
                .antMatchers(HttpMethod.POST, vehiclesEndpointsPOST).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, vehiclesEndpointsGET).permitAll()
                .antMatchers(HttpMethod.PUT, vehiclesEndpointsPUT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, vehiclesEndpointsDELETE).hasRole("ADMIN")

                // Reservations endpoint
                .antMatchers(HttpMethod.POST, reservationsEndpointsPOST).hasRole("CUSTOMER")
                .antMatchers(HttpMethod.GET, reservationsEndpointsGET).authenticated()
                .antMatchers(HttpMethod.PUT, reservationsEndpointsPUT).authenticated()
                .antMatchers(HttpMethod.DELETE, reservationsEndpointsDELETE).authenticated()

                .and()

                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();

        allowedOrigins.forEach(config::addAllowedOriginPattern);

        config.addAllowedHeader("*");
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
