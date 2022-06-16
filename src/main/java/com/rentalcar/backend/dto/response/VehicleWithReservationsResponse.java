package com.rentalcar.backend.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleWithReservationsResponse extends VehicleBaseResponse {
    private List<ReservationWithUserResponse> reservation;
}
