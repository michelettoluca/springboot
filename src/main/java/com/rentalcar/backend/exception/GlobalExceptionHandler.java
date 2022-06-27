package com.rentalcar.backend.exception;

import com.rentalcar.backend.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            IllegalStateException.class
    })
    protected ResponseEntity<Object> handleConflict() {
        HttpStatus status = HttpStatus.CONFLICT;
        String error = "This should be application specific";

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({
            UserNotFoundException.class,
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleUsernameNotFound() {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse error = new ErrorResponse("USER_NOT_FOUND", "User not found");

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({
            AuthenticationException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleAuthenticationFailed() {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse error = new ErrorResponse("AUTHENTICATION_FAILED", "Invalid user or password");

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({
            InvalidDateIntervalException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleInvalidDateInterval() {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse error = new ErrorResponse("INVALID_DATE_INTERVAL", "Invalid date interval");

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({
            UsernameTakenException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleUsernameTaken() {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse error = new ErrorResponse("USERNAME_TAKEN", "Username taken");

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({
            VehicleIsAlreadyBookedException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleVehicleIsAlreadyBooked() {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse error = new ErrorResponse("VEHICLE_NOT_AVAILABLE", "Vehicle is already booked for that period");

        return new ResponseEntity<>(error, status);
    }
}