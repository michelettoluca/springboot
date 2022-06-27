package com.rentalcar.backend.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)

public class VehicleIsAlreadyBookedException extends RuntimeException {
    public VehicleIsAlreadyBookedException() {
        super();
    }
}
