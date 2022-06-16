package com.rentalcar.backend.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReservationWithVehicleResponse extends ReservationBaseResponse {
    private VehicleBaseResponse vehicle;
}
