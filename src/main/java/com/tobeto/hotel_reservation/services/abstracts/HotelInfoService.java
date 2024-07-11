package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.services.dtos.hotelInfo.*;

public interface HotelInfoService {
    EntityWithPagination getAllHotelInfosWithPagination(PaginationRequest paginationRequest);

    GetHotelInfoResponse getHotelInfoById(Long hotelInfoId, String language);

    AddHotelInfoResponse addHotelInfo(AddHotelInfoRequest request, String language);

    UpdateHotelInfoResponse updateHotelInfoById(Long hotelInfoId, UpdateHotelInfoRequest request, String language);

    void deleteHotelInfoById(Long hotelInfoId, String language);
}
