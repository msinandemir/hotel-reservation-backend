package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.city.AddCityRequest;
import com.tobeto.hotel_reservation.services.dtos.city.AddCityResponse;
import com.tobeto.hotel_reservation.services.dtos.city.GetCityResponse;
import com.tobeto.hotel_reservation.services.dtos.city.UpdateCityResponse;
import com.tobeto.hotel_reservation.services.dtos.user.UpdateUserRequest;
import org.springframework.data.domain.Sort;

public interface CityService {
    EntityWithPagination getAllUsersWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetCityResponse getCityById(Long cityId, String language);

    AddCityResponse addCity(AddCityRequest request);

    UpdateCityResponse updateCityById(Long cityId, UpdateUserRequest request, String language);

    void deleteCityById(Long cityId, String language);
}
