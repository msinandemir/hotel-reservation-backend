package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.entities.concretes.Country;
import com.tobeto.hotel_reservation.services.dtos.country.*;

import java.util.List;

public interface CountryService {
    List<GetCountryResponse> getAllCountries();

    GetCountryResponse getCountryById(Long countryId, String language);

    AddCountryResponse addCountry(AddCountryRequest request);

    UpdateCountryResponse updateCountryById(Long countryId, UpdateCountryRequest request, String language);

    void deleteCountryById(Long countryId, String language);

    Country findCountryById(Long countryId, String language);
}
