package com.rentalcar.backend.controller;

import com.rentalcar.backend.dto.request.LoginRequest;
import com.rentalcar.backend.dto.response.LoginResponse;
import com.rentalcar.backend.mapper.AuthMapper;
import com.rentalcar.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "auth")
public class AuthController {
    private final AuthService authService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        String accessToken = this.authService.signIn(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        return new ResponseEntity<>(
                AuthMapper.toLoginResponse(accessToken),
                HttpStatus.OK
        );
    }
}
