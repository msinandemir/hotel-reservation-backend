package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.HotelInfo;
import com.tobeto.hotel_reservation.services.dtos.hotelInfo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelInfoMapper {
    HotelInfoMapper INSTANCE = Mappers.getMapper(HotelInfoMapper.class);

    @Mapping(source = "hotel.id", target = "hotelId")
    GetHotelInfoResponse getResponseFromHotelInfo(HotelInfo hotelInfo);

    @Mapping(target = "hotel.id", source = "hotelId")
    HotelInfo hotelInfoFromAddRequest(AddHotelInfoRequest request);

    @Mapping(source = "hotel.id", target = "hotelId")
    AddHotelInfoResponse addResponseFromHotelInfo(HotelInfo hotelInfo);

    @Mapping(target = "hotel.id", source = "hotelId")
    HotelInfo hotelInfoFromUpdateRequest(UpdateHotelInfoRequest request);

    @Mapping(source = "hotel.id", target = "hotelId")
    UpdateHotelInfoResponse updateResponseFromHotelInfo(HotelInfo hotelInfo);
}
