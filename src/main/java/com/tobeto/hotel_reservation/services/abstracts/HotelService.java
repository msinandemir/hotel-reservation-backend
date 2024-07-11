package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.entities.concretes.Hotel;
import com.tobeto.hotel_reservation.services.dtos.hotel.*;

public interface HotelService {
    EntityWithPagination getAllHotelsWithPagination(PaginationRequest paginationRequest);

    GetHotelResponse getHotelById(Long hotelId, String language);

    AddHotelResponse addHotel(AddHotelRequest request, String language);

    UpdateHotelResponse updateHotelById(Long hotelId, UpdateHotelRequest request, String language);

    void deleteHotelById(Long hotelId, String language);

    Hotel findHotelById(Long hotelId, String language);
}
