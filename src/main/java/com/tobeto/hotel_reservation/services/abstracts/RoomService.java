package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.Room;
import com.tobeto.hotel_reservation.services.dtos.room.*;
import org.springframework.data.domain.Sort;

public interface RoomService {
    EntityWithPagination getAllRoomsWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetRoomResponse getRoomById(Long roomId, String language);

    AddRoomResponse addRoom(AddRoomRequest request, String language);

    UpdateRoomResponse updateRoomById(Long roomId, UpdateRoomRequest request, String language);

    void deleteRoomById(Long roomId, String language);

    Room findRoomById(Long roomId, String language);
}
