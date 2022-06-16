package com.rentalcar.backend.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReservationFullResponse extends ReservationBaseResponse {
    private UserBaseResponse user;
    private VehicleBaseResponse vehicle;
}
