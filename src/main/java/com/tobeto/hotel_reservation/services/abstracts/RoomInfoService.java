package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.services.dtos.roomInfo.*;

public interface RoomInfoService {
    EntityWithPagination getAllRoomInfosWithPagination(PaginationRequest paginationRequest);

    GetRoomInfoResponse getRoomInfoById(Long roomInfoId, String language);

    AddRoomInfoResponse addRoomInfo(AddRoomInfoRequest request, String language);

    UpdateRoomInfoResponse updateRoomInfoById(Long roomInfoId, UpdateRoomInfoRequest request, String language);

    void deleteRoomInfoById(Long roomInfoId, String language);
}
