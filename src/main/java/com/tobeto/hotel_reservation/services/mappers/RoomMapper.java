package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Room;
import com.tobeto.hotel_reservation.services.dtos.room.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(source = "hotel.id", target = "hotelId")
    GetRoomResponse getResponseFromRoom(Room room);

    @Mapping(target = "hotel.id", source = "hotelId")
    Room roomFromAddRequest(AddRoomRequest request);

    @Mapping(source = "hotel.id", target = "hotelId")
    AddRoomResponse addResponseFromRoom(Room room);

    @Mapping(target = "hotel.id", source = "hotelId")
    Room roomFromUpdateRequest(UpdateRoomRequest request);

    @Mapping(source = "hotel.id", target = "hotelId")
    UpdateRoomResponse updateResponseFromRoom(Room room);
}
