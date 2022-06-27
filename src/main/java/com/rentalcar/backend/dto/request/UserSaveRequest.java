package com.rentalcar.backend.dto.request;

import com.rentalcar.backend.type.UserRole;
import lombok.Data;

@Data
public class UserSaveRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserRole role;
}
