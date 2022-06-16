package com.rentalcar.backend.repository.specification;

import com.rentalcar.backend.entity.Vehicle;
import com.rentalcar.backend.type.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;

@RequiredArgsConstructor
public class AvailableVehicles implements Specification<Vehicle> {
    private final LocalDate from;
    private final LocalDate to;

    @Override
    public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Subquery<Vehicle> subquery = query.subquery(Vehicle.class);

        Root<Vehicle> subqueryRoot = subquery.from(Vehicle.class);

        subquery.select(subqueryRoot.get("id"))
                .where(
                        criteriaBuilder.and(
                                criteriaBuilder.greaterThanOrEqualTo(subqueryRoot.join("reservations").get("endsAt"), from),
                                criteriaBuilder.lessThanOrEqualTo(subqueryRoot.join("reservations").get("beginsAt"), to),
                                criteriaBuilder.notEqual(subqueryRoot.join("reservations").get("status"), ReservationStatus.DENIED)
                        )
                );

        return criteriaBuilder.not(criteriaBuilder.in(root.get("id")).value(subquery));
    }
}
