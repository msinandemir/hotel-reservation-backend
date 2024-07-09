package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.enums.ReservationStatus;
import com.tobeto.hotel_reservation.services.dtos.reservation.*;
import org.springframework.data.domain.Sort;

public interface ReservationService {
    EntityWithPagination getAllReservationWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetReservationResponse getReservationById(Long reservationId, String language);

    EntityWithPagination getReservationsByUserId(Long userId, int pageNumber, int pageSize, Sort.Direction sortDirection);

    EntityWithPagination getReservationsByHotelId(Long hotelId, int pageNumber, int pageSize, Sort.Direction sortDirection);

    AddReservationResponse addReservation(AddReservationRequest request, String language);

    UpdateReservationResponse updateReservationById(Long reservationId, UpdateReservationRequest request, String language);

    ChangeReservationStatusResponse changeReservationStatusById(Long reservationId, ReservationStatus status, String language);

    void deleteReservationById(Long reservationId, String language);
}
