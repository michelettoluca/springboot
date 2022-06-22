package com.rentalcar.backend.dto;

import com.rentalcar.backend.type.UserRole;
import lombok.Data;

@Data
public class AuthenticatedUser {
    private final Integer id;
    private final UserRole role;

    public boolean isAdmin() {
        return role.equals(UserRole.ROLE_ADMIN);
    }

    public boolean isCustomer() {
        return role.equals(UserRole.ROLE_CUSTOMER);
    }
}
