package com.rentalcar.backend.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VehicleBaseResponse {
    private int id;
    private String brand;
    private String model;
    private LocalDate dateOfRegistration;
    private String plateNumber;
    private String type;
}
