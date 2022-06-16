package com.rentalcar.backend.service;

import com.rentalcar.backend.dto.request.ReservationSaveRequest;
import com.rentalcar.backend.entity.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation create(ReservationSaveRequest data);

    Reservation edit(Integer id, ReservationSaveRequest data);

    void deleteOneById(Integer id);

    List<Reservation> findAll();

    List<Reservation> findManyByUserId(Integer id);

    List<Reservation> findManyByVehicleId(Integer id);

    Reservation findOneById(Integer id);
}
