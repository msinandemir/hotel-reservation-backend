package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Country;
import com.tobeto.hotel_reservation.services.dtos.country.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    GetCountryResponse getResponseFromCountry(Country country);
    Country countryFromAddRequest(AddCountryRequest request);
    AddCountryResponse addResponseFromCountry(Country country);
    Country countryFromUpdateRequest(UpdateCountryRequest request);
    UpdateCountryResponse updateResponseFromCountry(Country country);
}
