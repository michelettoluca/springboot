package com.rentalcar.backend.repository.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

//    private List<User> getAllNoJpaRepository() {
//        this.entityManager.createQuery().getResultList();
//    }
}
