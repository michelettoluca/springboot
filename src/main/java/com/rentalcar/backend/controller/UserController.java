package com.rentalcar.backend.controller;

import com.rentalcar.backend.entity.User;
import com.rentalcar.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        ResponseEntity<List<User>> response;

        List<User> users = userService.findAll();
        response = new ResponseEntity<>(users, HttpStatus.OK);

        return response;
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<Optional<User>> getUserById(
            @PathVariable("id") Integer id
    ) {
        ResponseEntity<Optional<User>> response;

        Optional<User> user = userService.findById(id);

        response = user.isPresent()
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return response;
    }
}
