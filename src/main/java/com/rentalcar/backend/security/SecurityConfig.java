package com.rentalcar.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        final String[] publicPages = new String[]{"/"};
//        final String[] authenticatedPages = new String[]{"/profile/**", "/vehicles/**", "/reservations/**"};
//        final String[] adminPages = new String[]{"/admin/**"};
//        final String[] customerPages = new String[]{"/reservations/**"};

//        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

//        http
//                .authorizeRequests()
//                .antMatchers(publicPages).permitAll()
//                .antMatchers(authenticatedPages).authenticated()
//                .antMatchers(customerPages).access("hasRole('CUSTOMER')")
//                .antMatchers(adminPages).access("hasRole('ADMIN')")
//                .and().formLogin().loginPage("/sign-in")
//                .usernameParameter("username").passwordParameter("password")
//                .and().exceptionHandling().accessDeniedPage("/sign-in?status=error")
//                .and().csrf();

        http
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll()

                .and()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                .apply(CustomDsl.customDsl())

                .and()

                .csrf()
                .disable();

        return http
                .build();
    }
}
