package com.rentalcar.backend.mapper;

import com.rentalcar.backend.dto.request.ReservationSaveRequest;
import com.rentalcar.backend.dto.response.ReservationBaseResponse;
import com.rentalcar.backend.dto.response.ReservationFullResponse;
import com.rentalcar.backend.dto.response.ReservationWithUserResponse;
import com.rentalcar.backend.dto.response.ReservationWithVehicleResponse;
import com.rentalcar.backend.entity.Reservation;
import com.rentalcar.backend.entity.User;
import com.rentalcar.backend.entity.Vehicle;
import org.springframework.beans.BeanUtils;

public class ReservationMapper {

    private ReservationMapper() {
    }

    public static ReservationBaseResponse toReservationBaseResponse(Reservation reservation) {
        ReservationBaseResponse _reservation = new ReservationBaseResponse();

        _reservation.setId(reservation.getId());
        _reservation.setBeginsAt(reservation.getBeginsAt());
        _reservation.setEndsAt(reservation.getEndsAt());
        _reservation.setStatus(reservation.getStatus());

        return _reservation;
    }

    public static ReservationWithUserResponse toReservationWithUserResponse(Reservation reservation) {
        ReservationWithUserResponse _reservation = new ReservationWithUserResponse();
        BeanUtils.copyProperties(toReservationBaseResponse(reservation), _reservation);

        _reservation.setUser(UserMapper.toBaseUserResponse(reservation.getUser()));

        return _reservation;
    }

    public static ReservationWithVehicleResponse toReservationWithVehicleResponse(Reservation reservation) {
        ReservationWithVehicleResponse _reservation = new ReservationWithVehicleResponse();
        BeanUtils.copyProperties(toReservationBaseResponse(reservation), _reservation);

        _reservation.setVehicle(VehicleMapper.toBaseVehicleResponse(reservation.getVehicle()));

        return _reservation;
    }

    public static ReservationFullResponse toReservationFullResponse(Reservation reservation) {
        ReservationFullResponse _reservation = new ReservationFullResponse();
        BeanUtils.copyProperties(toReservationBaseResponse(reservation), _reservation);

        _reservation.setUser(UserMapper.toBaseUserResponse(reservation.getUser()));
        _reservation.setVehicle(VehicleMapper.toBaseVehicleResponse(reservation.getVehicle()));

        return _reservation;
    }

    public static Reservation toReservationEntity(ReservationSaveRequest data, User user, Vehicle vehicle) {
        Reservation reservation = new Reservation();

        reservation.setBeginsAt(data.getBeginsAt());
        reservation.setEndsAt(data.getEndsAt());
        reservation.setStatus(data.getStatus());
        reservation.setUser(user);
        reservation.setVehicle(vehicle);

        return reservation;
    }
}
