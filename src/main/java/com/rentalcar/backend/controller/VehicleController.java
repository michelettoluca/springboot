package com.rentalcar.backend.controller;

import com.rentalcar.backend.dto.request.VehicleSaveRequest;
import com.rentalcar.backend.dto.response.VehicleBaseResponse;
import com.rentalcar.backend.entity.Vehicle;
import com.rentalcar.backend.mapper.VehicleMapper;
import com.rentalcar.backend.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<VehicleBaseResponse>> listVehicles() {
        List<Vehicle> vehicles = vehicleService.findAll();

        return new ResponseEntity<>(
                vehicles
                        .stream()
                        .map(VehicleMapper::toBaseVehicleResponse)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<VehicleBaseResponse> create(
            @RequestBody VehicleSaveRequest data
    ) {
        Vehicle vehicle = this.vehicleService.create(data);

        return new ResponseEntity<>(
                VehicleMapper.toBaseVehicleResponse(vehicle),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "available", method = RequestMethod.GET)
    public ResponseEntity<List<VehicleBaseResponse>> findAvailable(
            @RequestParam("from") LocalDate from,
            @RequestParam("to") LocalDate to
    ) {
        List<Vehicle> vehicles = vehicleService.findAvailable(from, to);

        return new ResponseEntity<>(
                vehicles
                        .stream()
                        .map(VehicleMapper::toBaseVehicleResponse)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "by/id/{id}", method = RequestMethod.PUT)
    public ResponseEntity<VehicleBaseResponse> edit(
            @PathVariable("id") Integer id,
            @RequestBody VehicleSaveRequest data
    ) {
        Vehicle vehicle = this.vehicleService.edit(id, data);

        return new ResponseEntity<>(
                VehicleMapper.toBaseVehicleResponse(vehicle),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "by/id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteOne(
            @PathVariable("id") Integer id
    ) {
        this.vehicleService.deleteOneById(id);

        return new ResponseEntity<>(
                "User deleted",
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "by/{attribute}/{value}", method = RequestMethod.GET)
    public ResponseEntity<VehicleBaseResponse> getOne(
            @PathVariable("attribute") String attribute,
            @PathVariable("value") String value
    ) {

        Vehicle vehicle;

        switch (attribute) {
            case "id":
                vehicle = this.vehicleService.findOneById(Integer.parseInt(value));
                break;

            case "plate-number":
                vehicle = this.vehicleService.findOneByPlateNumber(value);
                break;

            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                VehicleMapper.toBaseVehicleResponse(vehicle),
                HttpStatus.OK
        );
    }
}
