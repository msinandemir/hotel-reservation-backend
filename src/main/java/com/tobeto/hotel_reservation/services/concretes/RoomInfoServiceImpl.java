package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.RoomInfoService;
import com.tobeto.hotel_reservation.services.dtos.roomInfo.*;
import org.springframework.data.domain.Sort;

public class RoomInfoServiceImpl implements RoomInfoService {
    @Override
    public EntityWithPagination getAllRoomInfosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public GetRoomInfoResponse getRoomInfoById(Long roomInfoId, String language) {
        return null;
    }

    @Override
    public AddRoomInfoResponse addRoomInfo(AddRoomInfoRequest request) {
        return null;
    }

    @Override
    public UpdateRoomInfoResponse updateRoomInfoById(Long roomInfoId, UpdateRoomInfoRequest request, String language) {
        return null;
    }

    @Override
    public void deleteRoomInfoById(Long roomInfoId, String language) {

    }
}
