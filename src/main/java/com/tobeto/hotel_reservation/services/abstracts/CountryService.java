package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.comment.UpdateCommentRequest;
import com.tobeto.hotel_reservation.services.dtos.country.AddCountryRequest;
import com.tobeto.hotel_reservation.services.dtos.country.AddCountryResponse;
import com.tobeto.hotel_reservation.services.dtos.country.GetCountryResponse;
import com.tobeto.hotel_reservation.services.dtos.country.UpdateCountryResponse;
import org.springframework.data.domain.Sort;

public interface CountryService {
    EntityWithPagination getAllCountriesWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetCountryResponse getCountryById(Long countryId, String language);

    AddCountryResponse addCountry(AddCountryRequest request);

    UpdateCountryResponse updateCountryById(Long countryId, UpdateCommentRequest request, String language);

    void deleteCountryById(Long countryId, String language);
}
