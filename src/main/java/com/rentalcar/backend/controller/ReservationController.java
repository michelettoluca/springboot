package com.rentalcar.backend.controller;

import com.rentalcar.backend.dto.AuthenticatedUser;
import com.rentalcar.backend.dto.request.ReservationSaveRequest;
import com.rentalcar.backend.dto.response.ReservationFullResponse;
import com.rentalcar.backend.entity.Reservation;
import com.rentalcar.backend.mapper.Mapper;
import com.rentalcar.backend.mapper.ReservationMapper;
import com.rentalcar.backend.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationFullResponse>> getAll() {
        List<Reservation> reservations = this.reservationService.findAll();

        return new ResponseEntity<>(
                Mapper.toList(reservations, ReservationMapper::toReservationFullResponse),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ReservationFullResponse> create(
            @RequestBody ReservationSaveRequest data
    ) {
        Reservation reservation = this.reservationService.create(data);

        return new ResponseEntity<>(
                ReservationMapper.toReservationFullResponse(reservation),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "by/{entity}/{id}")
    public ResponseEntity<List<ReservationFullResponse>> getManyByUserId(
            @PathVariable("entity") String entity,
            @PathVariable("id") Integer id
    ) {
        List<Reservation> reservations;

        switch (entity) {
            case "user-id":
                reservations = this.reservationService.findManyByUserId(id);
                break;

            case "vehicle-id":
                reservations = this.reservationService.findManyByVehicleId(id);
                break;

            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                Mapper.toList(reservations, ReservationMapper::toReservationFullResponse),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "by/id/{id}")
    public ResponseEntity<ReservationFullResponse> getOne(
            @PathVariable("id") Integer id
    ) {
        Reservation reservation = this.reservationService.findOneById(id);

        return new ResponseEntity<>(
                ReservationMapper.toReservationFullResponse(reservation),
                HttpStatus.OK
        );
    }

    @PutMapping(value = "by/id/{id}")
    public ResponseEntity<ReservationFullResponse> edit(
            @PathVariable("id") Integer id,
            @RequestBody ReservationSaveRequest data,
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser
    ) {
        if (!authenticatedUser.isAdmin() && !Objects.equals(authenticatedUser.getId(), data.getUserId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Reservation reservation = this.reservationService.edit(id, data);

        return new ResponseEntity<>(
                ReservationMapper.toReservationFullResponse(reservation),
                HttpStatus.OK
        );
    }

    @DeleteMapping(value = "by/id/{id}")
    public ResponseEntity<HttpStatus> delete(
            @PathVariable("id") Integer id,
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser
    ) {
        Reservation reservation = this.reservationService.findOneById(id);

        if (!authenticatedUser.isAdmin() && !Objects.equals(authenticatedUser.getId(), reservation.getUser().getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        this.reservationService.deleteOneById(id);

        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }
}
