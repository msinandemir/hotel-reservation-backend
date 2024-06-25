package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.City;
import com.tobeto.hotel_reservation.services.dtos.city.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(source = "country.id", target = "countryId")
    GetCityResponse getResponseFromCity(City city);

    @Mapping(target = "country.id", source = "countryId")
    City cityFromAddRequest(AddCityRequest request);

    @Mapping(source = "country.id", target = "countryId")
    AddCityResponse addResponseFromCity(City city);

    @Mapping(target = "country.id", source = "countryId")
    City cityFromUpdateRequest(UpdateCityRequest request);

    @Mapping(source = "country.id", target = "countryId")
    UpdateCityResponse updateResponseFromCity(City city);
}
