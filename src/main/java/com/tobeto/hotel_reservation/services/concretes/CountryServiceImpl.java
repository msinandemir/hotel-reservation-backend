package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.CountryService;
import com.tobeto.hotel_reservation.services.dtos.comment.UpdateCommentRequest;
import com.tobeto.hotel_reservation.services.dtos.country.AddCountryRequest;
import com.tobeto.hotel_reservation.services.dtos.country.AddCountryResponse;
import com.tobeto.hotel_reservation.services.dtos.country.GetCountryResponse;
import com.tobeto.hotel_reservation.services.dtos.country.UpdateCountryResponse;
import org.springframework.data.domain.Sort;

public class CountryServiceImpl implements CountryService {
    @Override
    public EntityWithPagination getAllCountriesWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public GetCountryResponse getCountryById(Long countryId, String language) {
        return null;
    }

    @Override
    public AddCountryResponse addCountry(AddCountryRequest request) {
        return null;
    }

    @Override
    public UpdateCountryResponse updateCountryById(Long countryId, UpdateCommentRequest request, String language) {
        return null;
    }

    @Override
    public void deleteCountryById(Long countryId, String language) {

    }
}
