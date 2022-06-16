package com.rentalcar.backend.service;

import com.rentalcar.backend.dto.request.UserSaveRequest;
import com.rentalcar.backend.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findOneById(Integer id);

    void deleteOneById(Integer id);

    User findOneByUsername(String username);

    User create(UserSaveRequest data);

    User edit(Integer id, UserSaveRequest data);
}
