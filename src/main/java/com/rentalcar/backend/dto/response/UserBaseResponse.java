package com.rentalcar.backend.dto.response;

import com.rentalcar.backend.type.UserRole;
import lombok.Data;

@Data
public class UserBaseResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private UserRole role;
}
