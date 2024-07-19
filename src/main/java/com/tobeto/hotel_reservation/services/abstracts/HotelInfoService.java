package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.hotelInfo.*;
import org.springframework.data.domain.Sort;

public interface HotelInfoService {
    EntityWithPagination getAllHotelInfosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy);

    GetHotelInfoResponse getHotelInfoById(Long hotelInfoId, String language);

    GetHotelInfoResponse getHotelInfoByHotelId(Long hotelId, String language);

    AddHotelInfoResponse addHotelInfo(AddHotelInfoRequest request, String language);

    UpdateHotelInfoResponse updateHotelInfoById(Long hotelInfoId, UpdateHotelInfoRequest request, String language);

    void deleteHotelInfoById(Long hotelInfoId, String language);
}
