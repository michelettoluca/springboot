package com.rentalcar.backend.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReservationWithUserResponse extends ReservationBaseResponse {
    private UserBaseResponse user;
}
