package com.rentalcar.backend.mapper;

import com.rentalcar.backend.dto.request.UserSaveRequest;
import com.rentalcar.backend.dto.response.ReservationWithVehicleResponse;
import com.rentalcar.backend.dto.response.UserBaseResponse;
import com.rentalcar.backend.dto.response.UserWithReservationsResponse;
import com.rentalcar.backend.entity.User;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper() {
    }

    public static UserBaseResponse toBaseUserResponse(User user) {
        UserBaseResponse _user = new UserBaseResponse();

        _user.setId(user.getId());
        _user.setFirstName(user.getFirstName());
        _user.setLastName(user.getLastName());
        _user.setUsername(user.getUsername());
        _user.setRole(user.getRole());

        return _user;
    }

    public static UserWithReservationsResponse toUserWithReservationsResponse(User user) {
        UserWithReservationsResponse _user = new UserWithReservationsResponse();
        BeanUtils.copyProperties(toBaseUserResponse(user), _user);

        List<ReservationWithVehicleResponse> _reservations = user
                .getReservations()
                .stream()
                .map(ReservationMapper::toReservationWithVehicleResponse)
                .collect(Collectors.toList());

        _user.setReservations(_reservations);

        return _user;
    }

    public static User toUserEntity(UserSaveRequest data) {
        User user = new User();
        
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setUsername(data.getUsername());
        user.setPassword(data.getPassword());
        user.setRole(data.getRole());

        return user;
    }
}
