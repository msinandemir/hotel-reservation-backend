package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.entities.concretes.City;
import com.tobeto.hotel_reservation.services.dtos.city.*;

import java.util.List;

public interface CityService {
    List<GetCityResponse> getAllCities();

    GetCityResponse getCityById(Long cityId, String language);

    AddCityResponse addCity(AddCityRequest request, String language);

    UpdateCityResponse updateCityById(Long cityId, UpdateCityRequest request, String language);

    void deleteCityById(Long cityId, String language);

    City findCityById(Long cityId, String language);
}
