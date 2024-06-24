package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Hotel;
import com.tobeto.hotel_reservation.services.dtos.hotel.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "address.id", target = "addressId")
    GetHotelResponse getResponseFromHotel(Hotel hotel);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "address.id", source = "addressId")
    Hotel hotelFromAddRequest(AddHotelRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "address.id", target = "addressId")
    AddHotelResponse addResponseFromHotel(Hotel hotel);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "address.id", source = "addressId")
    Hotel hotelFromUpdateRequest(UpdateHotelRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "address.id", target = "addressId")
    UpdateHotelResponse updateResponseFromHotel(Hotel hotel);
}
