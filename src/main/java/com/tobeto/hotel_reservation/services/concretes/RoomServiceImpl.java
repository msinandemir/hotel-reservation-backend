package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.RoomService;
import com.tobeto.hotel_reservation.services.dtos.room.*;
import org.springframework.data.domain.Sort;

public class RoomServiceImpl implements RoomService {
    @Override
    public EntityWithPagination getAllRoomsWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public GetRoomResponse getRoomById(Long roomId, String language) {
        return null;
    }

    @Override
    public AddRoomResponse addRoom(AddRoomRequest request) {
        return null;
    }

    @Override
    public UpdateRoomResponse updateRoomById(Long roomId, UpdateRoomRequest request, String language) {
        return null;
    }

    @Override
    public void deleteRoomById(Long roomId, String language) {

    }
}
