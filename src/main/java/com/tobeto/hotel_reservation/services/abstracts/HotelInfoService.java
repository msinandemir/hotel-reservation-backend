package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.hotelInfo.*;
import org.springframework.data.domain.Sort;

public interface HotelInfoService {
    EntityWithPagination getAllUsersWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetHotelInfoResponse getHotelInfoById(Long hotelInfoId, String language);

    AddHotelInfoResponse addHotelInfo(AddHotelInfoRequest request);

    UpdateHotelResponse updateHotelInfoById(Long hotelInfoId, UpdateHotelInfoRequest request, String language);

    void deleteHotelInfoById(Long hotelInfoId, String language);
}
