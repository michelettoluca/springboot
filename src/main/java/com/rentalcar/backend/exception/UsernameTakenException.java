package com.rentalcar.backend.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)

public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException() {
        super();
    }
}
