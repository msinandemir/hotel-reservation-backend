package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Hotel;
import com.tobeto.hotel_reservation.services.dtos.hotel.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    GetHotelResponse getResponseFromHotel(Hotel hotel);
    Hotel hotelFromAddRequest(AddHotelRequest request);
    AddHotelResponse addResponseFromHotel(Hotel hotel);
    Hotel hotelFromUpdateRequest(UpdateHotelRequest request);
    UpdateHotelResponse updateResponseFromHotel(Hotel hotel);
}
