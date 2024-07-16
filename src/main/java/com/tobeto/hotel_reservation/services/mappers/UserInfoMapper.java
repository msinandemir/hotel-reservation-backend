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
    GetUserInfoResponse getResponseFromUserInfo(UserInfo userInfo);

    @Mapping(target = "user.id", source = "userId")
    UserInfo userInfoFromAddRequest(AddUserInfoRequest request);

    @Mapping(source = "user.id", target = "userId")
    AddUserInfoResponse addResponseFromUserInfo(UserInfo userInfo);

    @Mapping(target = "user.id", source = "userId")
    UserInfo userInfoFromUpdateRequest(UpdateUserInfoRequest request);

    @Mapping(source = "user.id", target = "userId")
    UpdateUserInfoResponse updateResponseFromUserInfo(UserInfo userInfo);
}
