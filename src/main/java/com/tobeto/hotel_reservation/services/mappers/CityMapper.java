package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.City;
import com.tobeto.hotel_reservation.services.dtos.city.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    GetCityResponse getResponseFromCity(City city);
    City cityFromAddRequest(AddCityRequest request);
    AddCityResponse addResponseFromCity(City city);
    City cityFromUpdateRequest(UpdateCityRequest request);
    UpdateCityResponse updateResponseFromCity(City city);
}
