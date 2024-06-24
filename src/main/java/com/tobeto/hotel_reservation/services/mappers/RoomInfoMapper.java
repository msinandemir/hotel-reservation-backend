package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.RoomInfo;
import com.tobeto.hotel_reservation.services.dtos.roomInfo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomInfoMapper {
    RoomInfoMapper INSTANCE = Mappers.getMapper(RoomInfoMapper.class);

    @Mapping(source = "room.id", target = "roomId")
    GetRoomInfoResponse getResponseFromRoomInfo(RoomInfo roomInfo);

    @Mapping(target = "room.id", source = "roomId")
    RoomInfo roomInfoFromAddRequest(AddRoomInfoRequest request);

    @Mapping(source = "room.id", target = "roomId")
    AddRoomInfoResponse addResponseFromRoomInfo(RoomInfo roomInfo);

    @Mapping(target = "room.id", source = "roomId")
    RoomInfo roomInfoFromUpdateRequest(UpdateRoomInfoRequest request);

    @Mapping(source = "room.id", target = "roomId")
    UpdateRoomInfoResponse updateResponseFromRoomInfo(RoomInfo roomInfo);
}
