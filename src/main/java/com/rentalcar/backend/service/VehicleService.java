package com.rentalcar.backend.service;

import com.rentalcar.backend.entity.Vehicle;
import com.rentalcar.backend.repository.vehicle.JpaVehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final JpaVehicleRepository vehicleRepository;

    public VehicleService(JpaVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }
}
