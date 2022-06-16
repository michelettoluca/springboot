package com.rentalcar.backend.dto.request;

import com.rentalcar.backend.type.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationSaveRequest {
    private LocalDate beginsAt;
    private LocalDate endsAt;
    private ReservationStatus status;
    private Integer userId;
    private Integer vehicleId;
}
