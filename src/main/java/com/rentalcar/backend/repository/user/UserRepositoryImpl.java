package com.rentalcar.backend.repository.user;

import com.rentalcar.backend.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@NoArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User asd(Integer id) {
        return (User) this.entityManager
                .createQuery("from User where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
