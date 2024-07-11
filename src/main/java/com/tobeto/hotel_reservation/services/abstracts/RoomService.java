package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.entities.concretes.Room;
import com.tobeto.hotel_reservation.services.dtos.room.*;


public interface RoomService {
    EntityWithPagination getAllRoomsWithPagination(PaginationRequest paginationRequest);

    GetRoomResponse getRoomById(Long roomId, String language);

    GetRoomResponse getFindAvailableRoomsByTypeAndDate(FindAvailableRoomsByTypeAndDateRequest request, String language);

    AddRoomResponse addRoom(AddRoomRequest request, String language);

    UpdateRoomResponse updateRoomById(Long roomId, UpdateRoomRequest request, String language);

    void deleteRoomById(Long roomId, String language);

    Room findRoomById(Long roomId, String language);
}
