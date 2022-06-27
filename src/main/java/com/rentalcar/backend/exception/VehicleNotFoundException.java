package com.rentalcar.backend.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException() {
        super();
    }
}
