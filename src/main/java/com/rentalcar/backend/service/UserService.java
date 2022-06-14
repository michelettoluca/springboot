package com.rentalcar.backend.service;

import com.rentalcar.backend.entity.User;
import com.rentalcar.backend.repository.user.JpaUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final JpaUserRepository userRepository;

    public UserService(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public User aa(Integer id) {
        return this.userRepository.asd(id);
    }
}
