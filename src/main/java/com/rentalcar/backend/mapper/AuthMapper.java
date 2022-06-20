package com.rentalcar.backend.mapper;

import com.rentalcar.backend.dto.response.LoginResponse;

public class AuthMapper {

    private AuthMapper() {
    }

    public static LoginResponse toLoginResponse(String accessToken) {
        LoginResponse loginResponse = new LoginResponse();
        
        loginResponse.setAccessToken(accessToken);

        return loginResponse;
    }
}
