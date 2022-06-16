package com.rentalcar.backend.dto.response;

import com.rentalcar.backend.type.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationBaseResponse {
    private int id;
    private LocalDate beginsAt;
    private LocalDate endsAt;
    private ReservationStatus status;
}
