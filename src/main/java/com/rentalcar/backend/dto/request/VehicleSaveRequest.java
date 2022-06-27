package com.rentalcar.backend.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VehicleSaveRequest {
    private Integer id;
    private String brand;
    private String model;
    private String plateNumber;
    private LocalDate dateOfRegistration;
    private String type;
}
