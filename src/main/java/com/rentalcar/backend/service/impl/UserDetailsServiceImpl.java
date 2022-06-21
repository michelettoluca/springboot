package com.rentalcar.backend.service.impl;

import com.rentalcar.backend.security.AuthenticatedUser;
import com.rentalcar.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.rentalcar.backend.entity.User user = this.userService.findOneByUsername(username);

        if (user == null) throw new UsernameNotFoundException("User not found");

        String[] roles = new String[]{"ROLE_" + user.getRole()};
//        Collection<SimpleGrantedAuthority> authorities = Arrays
//                .stream(roles)
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());

        return new AuthenticatedUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                roles);
//
//        return User.withUsername(user.getUsername())
//                .password(user.getPassword())
////                .disabled(user.isActive())
//                .disabled(false)
//                .authorities(roles)
//                .build();
    }
}
