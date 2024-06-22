package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Photo;
import com.tobeto.hotel_reservation.services.dtos.photo.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhotoMapper {

    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);

    GetPhotoResponse getResponseFromPhoto(Photo photo);
    Photo photoFromAddRequest(AddPhotoRequest request);
    AddPhotoResponse addResponseFromPhoto(Photo photo);
    Photo photoFromUpdateRequest(UpdatePhotoRequest request);
    UpdatePhotoResponse updateResponseFromPhoto(Photo photo);
}
