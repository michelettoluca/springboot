package com.rentalcar.backend.repository.user;

import com.rentalcar.backend.entity.Reservation;
import com.rentalcar.backend.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

//@NoArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User asd(Integer id) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);

        Join<User, Reservation> reservations = root.join("reservations");

        return null;
    }
}
