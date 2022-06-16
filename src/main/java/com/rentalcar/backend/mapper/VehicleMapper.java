package com.rentalcar.backend.mapper;

import com.rentalcar.backend.dto.request.VehicleSaveRequest;
import com.rentalcar.backend.dto.response.ReservationWithUserResponse;
import com.rentalcar.backend.dto.response.VehicleBaseResponse;
import com.rentalcar.backend.dto.response.VehicleWithReservationsResponse;
import com.rentalcar.backend.entity.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleMapper {
    private VehicleMapper() {
    }

    public static VehicleBaseResponse toBaseVehicleResponse(Vehicle vehicle) {
        VehicleBaseResponse _vehicle = new VehicleBaseResponse();

        _vehicle.setId(vehicle.getId());
        _vehicle.setBrand(vehicle.getBrand());
        _vehicle.setModel(vehicle.getModel());
        _vehicle.setDateOfRegistration(vehicle.getDateOfRegistration());
        _vehicle.setPlateNumber(vehicle.getPlateNumber());
        _vehicle.setType(vehicle.getType());

        return _vehicle;
    }

    public static VehicleWithReservationsResponse toUserWithReservationsResponse(Vehicle vehicle) {
        VehicleWithReservationsResponse _vehicle = (VehicleWithReservationsResponse) toBaseVehicleResponse(vehicle);

        List<ReservationWithUserResponse> _reservations = vehicle
                .getReservations()
                .stream()
                .map(ReservationMapper::toReservationWithUserResponse)
                .collect(Collectors.toList());

        _vehicle.setReservation(_reservations);

        return _vehicle;
    }

    public static Vehicle toVehicleEntity(VehicleSaveRequest data) {
        Vehicle vehicle = new Vehicle();

        vehicle.setBrand(data.getBrand());
        vehicle.setModel(data.getModel());
        vehicle.setDateOfRegistration(data.getDateOfRegistration());
        vehicle.setPlateNumber(data.getPlateNumber());
        vehicle.setType(data.getType());

        return vehicle;
    }
}
