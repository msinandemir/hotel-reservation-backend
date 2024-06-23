package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.CityService;
import com.tobeto.hotel_reservation.services.dtos.city.AddCityRequest;
import com.tobeto.hotel_reservation.services.dtos.city.AddCityResponse;
import com.tobeto.hotel_reservation.services.dtos.city.GetCityResponse;
import com.tobeto.hotel_reservation.services.dtos.city.UpdateCityResponse;
import com.tobeto.hotel_reservation.services.dtos.user.UpdateUserRequest;
import org.springframework.data.domain.Sort;

public class CityServiceImpl implements CityService {
    @Override
    public EntityWithPagination getAllCitiesWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public GetCityResponse getCityById(Long cityId, String language) {
        return null;
    }

    @Override
    public AddCityResponse addCity(AddCityRequest request) {
        return null;
    }

    @Override
    public UpdateCityResponse updateCityById(Long cityId, UpdateUserRequest request, String language) {
        return null;
    }

    @Override
    public void deleteCityById(Long cityId, String language) {

    }
}
