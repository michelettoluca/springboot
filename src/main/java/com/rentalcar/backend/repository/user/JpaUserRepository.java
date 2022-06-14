package com.rentalcar.backend.repository.user;

import com.rentalcar.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Integer>, UserRepository {
}
