package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.roomInfo.*;
import org.springframework.data.domain.Sort;

public interface RoomInfoService {
    EntityWithPagination getAllRoomInfosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetRoomInfoResponse getRoomInfoById(Long roomInfoId, String language);

    AddRoomInfoResponse addRoomInfo(AddRoomInfoRequest request, String language);

    UpdateRoomInfoResponse updateRoomInfoById(Long roomInfoId, UpdateRoomInfoRequest request, String language);

    void deleteRoomInfoById(Long roomInfoId, String language);
}
