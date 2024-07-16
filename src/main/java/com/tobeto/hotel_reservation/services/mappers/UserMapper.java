package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.User;
import com.tobeto.hotel_reservation.services.dtos.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "authorities", target = "role")
    GetUserResponse getResponseFromUser(User user);

    @Mapping(target = "address.id", source = "addressId")
    User userFromAddRequest(AddUserRequest request);

    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "authorities", target = "role")
    AddUserResponse addResponseFromUser(User user);

    @Mapping(target = "address.id", source = "addressId")
    User userFromUpdateRequest(UpdateUserRequest request);

    @Mapping(source = "authorities", target = "role")
    @Mapping(source = "address.id", target = "addressId")
    UpdateUserResponse updateResponseFromUser(User user);

    @Mapping(source = "authorities", target = "role")
    @Mapping(source = "address.id", target = "addressId")
    ChangeUserRoleResponse changeRolesResponseFromUser(User user);
}
