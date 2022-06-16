package com.rentalcar.backend.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserWithReservationsResponse extends UserBaseResponse {
    private List<ReservationWithVehicleResponse> reservations;
}
