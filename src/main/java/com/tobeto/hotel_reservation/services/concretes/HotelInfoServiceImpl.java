package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.HotelInfoService;
import com.tobeto.hotel_reservation.services.dtos.hotelInfo.*;
import org.springframework.data.domain.Sort;

public class HotelInfoServiceImpl implements HotelInfoService {
    @Override
    public EntityWithPagination getAllHotelInfosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public GetHotelInfoResponse getHotelInfoById(Long hotelInfoId, String language) {
        return null;
    }

    @Override
    public AddHotelInfoResponse addHotelInfo(AddHotelInfoRequest request) {
        return null;
    }

    @Override
    public UpdateHotelResponse updateHotelInfoById(Long hotelInfoId, UpdateHotelInfoRequest request, String language) {
        return null;
    }

    @Override
    public void deleteHotelInfoById(Long hotelInfoId, String language) {

    }
}
