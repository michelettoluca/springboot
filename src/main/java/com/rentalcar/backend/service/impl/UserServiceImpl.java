package com.rentalcar.backend.service.impl;

import com.rentalcar.backend.dto.request.UserSaveRequest;
import com.rentalcar.backend.entity.User;
import com.rentalcar.backend.exception.UserNotFoundException;
import com.rentalcar.backend.mapper.UserMapper;
import com.rentalcar.backend.repository.UserRepository;
import com.rentalcar.backend.service.UserService;
import com.rentalcar.backend.type.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository
                .findAll();
    }

    @Override
    public User findOneById(Integer id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteOneById(Integer id) {
        this.userRepository
                .deleteById(id);
    }

    @Override
    public User findOneByUsername(String username) {
        return this.userRepository
                .findUserByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User create(UserSaveRequest data) {
        User user = UserMapper.toUserEntity(data);

        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (user.getRole() == null) user.setRole(UserRole.CUSTOMER);

        return this.userRepository
                .save(user);
    }

    @Override
    public User edit(Integer id, UserSaveRequest data) {
        User user = this.userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        if (data.getFirstName() != null) user.setFirstName(data.getFirstName());
        if (data.getLastName() != null) user.setLastName(data.getLastName());
        if (data.getUsername() != null) user.setUsername(data.getUsername());
        if (data.getPassword() != null) user.setPassword(data.getPassword());
        if (data.getRole() != null) user.setRole(data.getRole());

        return this.userRepository
                .save(user);
    }
}
