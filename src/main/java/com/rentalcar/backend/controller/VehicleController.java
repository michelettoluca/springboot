package com.rentalcar.backend.controller;

import com.rentalcar.backend.entity.Vehicle;
import com.rentalcar.backend.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
//    @ResponseBody
    public ResponseEntity<List<Vehicle>> listVehicles() {
        ResponseEntity<List<Vehicle>> response;

        List<Vehicle> users = vehicleService.findAll();
        response = new ResponseEntity<>(users, HttpStatus.OK);

        return response;
    }
}
