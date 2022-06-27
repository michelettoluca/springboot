package com.rentalcar.backend.controller;

import com.rentalcar.backend.dto.AuthenticatedUser;
import com.rentalcar.backend.dto.request.UserSaveRequest;
import com.rentalcar.backend.dto.response.UserBaseResponse;
import com.rentalcar.backend.entity.User;
import com.rentalcar.backend.mapper.Mapper;
import com.rentalcar.backend.mapper.UserMapper;
import com.rentalcar.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserBaseResponse>> getAll() {
        SecurityContextHolder.getContext().getAuthentication();

        List<User> users = this.userService.findAll();

        return new ResponseEntity<>(
                Mapper.toList(users, UserMapper::toBaseUserResponse),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<UserBaseResponse> create(
            @RequestBody UserSaveRequest data
    ) {
        User user = this.userService.create(data);

        return new ResponseEntity<>(
                UserMapper.toBaseUserResponse(user),
                HttpStatus.OK
        );
    }

    @PutMapping(value = "by/id/{id}")
    public ResponseEntity<UserBaseResponse> edit(
            @PathVariable("id") Integer id,
            @RequestBody UserSaveRequest data,
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser
    ) {
        if (!authenticatedUser.isAdmin()) {
            if (!Objects.equals(authenticatedUser.getId(), id)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            data.setRole(null);
        }

        User user = this.userService.edit(id, data);

        return new ResponseEntity<>(
                UserMapper.toBaseUserResponse(user),
                HttpStatus.OK
        );
    }

    @DeleteMapping(value = "by/id/{id}")
    public ResponseEntity<HttpStatus> deleteOne(
            @PathVariable("id") Integer id,
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser
    ) {
        if (!authenticatedUser.isAdmin() && !Objects.equals(authenticatedUser.getId(), id)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        this.userService.deleteOneById(id);

        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    @GetMapping(value = "by/{attribute}/{value}")
    public ResponseEntity<UserBaseResponse> getOne(
            @PathVariable("attribute") String attribute,
            @PathVariable("value") String value
    ) {
        User user;

        switch (attribute) {
            case "id":
                user = this.userService.findOneById(Integer.parseInt(value));
                break;

            case "username":
                user = this.userService.findOneByUsername(value);
                break;

            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                UserMapper.toBaseUserResponse(user),
                HttpStatus.OK
        );
    }
}
