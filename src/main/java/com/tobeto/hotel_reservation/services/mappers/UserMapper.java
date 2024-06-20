package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.User;
import com.tobeto.hotel_reservation.services.dtos.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    GetUserResponse getResponseFromUser(User user);
    User userFromAddRequest(AddUserRequest request);
    AddUserResponse addResponseFromUser(User user);
    User userFromUpdateRequest(UpdateUserRequest request);
    UpdateUserResponse updateResponseFromUser(User user);
}
