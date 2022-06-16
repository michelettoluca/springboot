package com.rentalcar.backend.service;

import com.rentalcar.backend.dto.request.VehicleSaveRequest;
import com.rentalcar.backend.entity.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();

    List<Vehicle> findAvailable(LocalDate from, LocalDate to);

    Vehicle findOneById(Integer id);

    void deleteOneById(Integer id);

    Vehicle findOneByPlateNumber(String username);

    Vehicle create(VehicleSaveRequest data);

    Vehicle edit(Integer id, VehicleSaveRequest data);
}
