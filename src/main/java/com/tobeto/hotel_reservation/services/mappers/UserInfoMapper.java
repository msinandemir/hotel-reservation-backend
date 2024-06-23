package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.UserInfo;
import com.tobeto.hotel_reservation.services.dtos.userInfo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInfoMapper {
    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "address.id", target = "addressId")
    GetUserInfoResponse getResponseFromUserInfo(UserInfo userInfo);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "address.id", source = "addressId")
    UserInfo userInfoFromAddRequest(AddUserInfoRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "address.id", target = "addressId")
    AddUserInfoResponse addResponseFromUserInfo(UserInfo userInfo);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "address.id", source = "addressId")
    UserInfo userInfoFromUpdateRequest(UpdateUserInfoRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "address.id", target = "addressId")
    UpdateUserInfoResponse updateResponseFromUserInfo(UserInfo userInfo);
}
