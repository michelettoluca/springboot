package com.rentalcar.backend.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super();
    }
}
