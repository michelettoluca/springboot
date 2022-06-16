package com.rentalcar.backend.service.impl;

import com.rentalcar.backend.dto.request.VehicleSaveRequest;
import com.rentalcar.backend.entity.Vehicle;
import com.rentalcar.backend.mapper.VehicleMapper;
import com.rentalcar.backend.repository.VehicleRepository;
import com.rentalcar.backend.repository.specification.AvailableVehicles;
import com.rentalcar.backend.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository
                .findAll();
    }

    @Override
    public List<Vehicle> findAvailable(LocalDate from, LocalDate to) {
        return this.vehicleRepository
                .findAll(new AvailableVehicles(from, to));
    }

    @Override
    public Vehicle findOneById(Integer id) {
        return this.vehicleRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteOneById(Integer id) {
        this.vehicleRepository
                .deleteById(id);
    }

    @Override
    public Vehicle findOneByPlateNumber(String plateNumber) {
        return this.vehicleRepository
                .findVehicleByPlateNumber(plateNumber)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Vehicle create(VehicleSaveRequest data) {
        Vehicle vehicle = VehicleMapper.toVehicleEntity(data);

        return this.vehicleRepository
                .save(vehicle);
    }

    @Override
    public Vehicle edit(Integer id, VehicleSaveRequest data) {
        Vehicle vehicle = this.vehicleRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        if (data.getBrand() != null) vehicle.setBrand(data.getBrand());
        if (data.getModel() != null) vehicle.setModel(data.getModel());
        if (data.getPlateNumber() != null) vehicle.setPlateNumber(data.getPlateNumber());
        if (data.getDateOfRegistration() != null) vehicle.setDateOfRegistration(data.getDateOfRegistration());
        if (data.getType() != null) vehicle.setType(data.getType());

        return this.vehicleRepository
                .save(vehicle);
    }
}
