package com.rentalcar.backend.service.impl;

import com.rentalcar.backend.dto.request.ReservationSaveRequest;
import com.rentalcar.backend.entity.Reservation;
import com.rentalcar.backend.entity.User;
import com.rentalcar.backend.entity.Vehicle;
import com.rentalcar.backend.mapper.ReservationMapper;
import com.rentalcar.backend.repository.ReservationRepository;
import com.rentalcar.backend.service.ReservationService;
import com.rentalcar.backend.service.UserService;
import com.rentalcar.backend.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final VehicleService vehicleService;

    @Override
    public List<Reservation> findAll() {
        return this.reservationRepository
                .findAll();
    }

    @Override
    public Reservation findOneById(Integer id) {
        return this.reservationRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Reservation> findManyByUserId(Integer id) {
        return this.reservationRepository
                .findAllByUserId(id);
    }

    @Override
    public List<Reservation> findManyByVehicleId(Integer id) {
        return this.reservationRepository
                .findAllByVehicleId(id);
    }

    @Override
    public void deleteOneById(Integer id) {
        this.reservationRepository
                .deleteById(id);
    }

    @Override
    public Reservation create(ReservationSaveRequest data) {
        User user = this.userService.findOneById(data.getUserId());
        Vehicle vehicle = this.vehicleService.findOneById(data.getVehicleId());
        
        Reservation reservation = ReservationMapper.toReservationEntity(data, user, vehicle);

        return this.reservationRepository
                .save(reservation);
    }

    @Override
    public Reservation edit(Integer id, ReservationSaveRequest data) {
        Reservation reservation = this.reservationRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        if (data.getBeginsAt() != null) reservation.setBeginsAt(data.getBeginsAt());
        if (data.getEndsAt() != null) reservation.setEndsAt(data.getEndsAt());
        if (data.getStatus() != null) reservation.setStatus(data.getStatus());
        if (data.getUserId() != null) {
            User user = this.userService.findOneById(data.getUserId());
            reservation.setUser(user);
        }
        if (data.getVehicleId() != null) {
            Vehicle vehicle = this.vehicleService.findOneById(data.getUserId());
            reservation.setVehicle(vehicle);
        }

        return this.reservationRepository
                .save(reservation);
    }
}
