package com.rentalcar.backend.repository.reservation;

import com.rentalcar.backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReservationRepository extends JpaRepository<Reservation, Integer>, ReservationRepository {
}
