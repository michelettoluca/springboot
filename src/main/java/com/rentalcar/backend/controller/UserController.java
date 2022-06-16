package com.rentalcar.backend.controller;

import com.rentalcar.backend.dto.request.UserSaveRequest;
import com.rentalcar.backend.dto.response.UserBaseResponse;
import com.rentalcar.backend.entity.User;
import com.rentalcar.backend.mapper.UserMapper;
import com.rentalcar.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserBaseResponse>> getAll() {
        List<User> users = this.userService.findAll();

        return new ResponseEntity<>(
                users
                        .stream()
                        .map(UserMapper::toBaseUserResponse)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserBaseResponse> create(
            @RequestBody UserSaveRequest data
    ) {
        User tmpUser = this.userService.create(data);

        return new ResponseEntity<>(
                UserMapper.toBaseUserResponse(tmpUser),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "by/id/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserBaseResponse> edit(
            @PathVariable("id") Integer id,
            @RequestBody UserSaveRequest data
    ) {
        User user = this.userService.edit(id, data);

        return new ResponseEntity<>(
                UserMapper.toBaseUserResponse(user),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "by/id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteOne(
            @PathVariable("id") Integer id
    ) {
        this.userService.deleteOneById(id);

        return new ResponseEntity<>(
                "User deleted",
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "by/{attribute}/{value}", method = RequestMethod.GET)
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
