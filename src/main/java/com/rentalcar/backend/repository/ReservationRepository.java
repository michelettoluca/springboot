package com.rentalcar.backend.repository;

import com.rentalcar.backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>, JpaSpecificationExecutor<Reservation> {
    List<Reservation> findAllByUserId(Integer id);

    List<Reservation> findAllByVehicleId(Integer id);
}
