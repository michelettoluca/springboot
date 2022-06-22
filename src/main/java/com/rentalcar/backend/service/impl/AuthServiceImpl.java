package com.rentalcar.backend.service.impl;

import com.rentalcar.backend.entity.User;
import com.rentalcar.backend.mapper.UserMapper;
import com.rentalcar.backend.service.AuthService;
import com.rentalcar.backend.service.UserService;
import com.rentalcar.backend.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    //    TODO: Catch exceptions
    @Override
    public String signIn(String username, String password) {

//        TODO: ???
//              UserDetails user = this.userDetailsService.loadUserByUsername(username);
        User user = this.userService.findOneByUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        this.authenticationManager.authenticate(authenticationToken);

        return jwtUtils.encode(UserMapper.toBaseUserResponse(user));
    }
}
