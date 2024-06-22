package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Room;
import com.tobeto.hotel_reservation.services.dtos.room.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    GetRoomResponse getResponseFromRoom(Room room);
    Room roomFromAddRequest(AddRoomRequest request);
    AddRoomResponse addResponseFromRoom(Room room);
    Room roomFromUpdateRequest(UpdateRoomRequest request);
    UpdateRoomResponse updateResponseFromRoom(Room room);
}
