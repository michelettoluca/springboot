package com.rentalcar.backend.repository;

import com.rentalcar.backend.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>, JpaSpecificationExecutor<Vehicle> {
    Optional<Vehicle> findVehicleByPlateNumber(String plateNumber);
}
