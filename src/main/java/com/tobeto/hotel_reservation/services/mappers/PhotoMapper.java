package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Photo;
import com.tobeto.hotel_reservation.services.dtos.photo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhotoMapper {

    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);

    @Mapping(source = "hotel.id", target = "hotelId")
    GetPhotoResponse getResponseFromPhoto(Photo photo);

    @Mapping(target = "hotel.id", source = "hotelId")
    Photo photoFromAddRequest(AddPhotoRequest request);

    @Mapping(source = "hotel.id", target = "hotelId")
    AddPhotoResponse addResponseFromPhoto(Photo photo);

    @Mapping(target = "hotel.id", source = "hotelId")
    Photo photoFromUpdateRequest(UpdatePhotoRequest request);

    @Mapping(source = "hotel.id", target = "hotelId")
    UpdatePhotoResponse updateResponseFromPhoto(Photo photo);
}
