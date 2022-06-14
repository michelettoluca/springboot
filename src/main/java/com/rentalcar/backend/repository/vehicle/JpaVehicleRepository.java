package com.rentalcar.backend.repository.vehicle;

import com.rentalcar.backend.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaVehicleRepository extends JpaRepository<Vehicle, Integer>, VehicleRepository {
}
