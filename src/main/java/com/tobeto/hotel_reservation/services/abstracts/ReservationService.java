package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.reservation.*;
import org.springframework.data.domain.Sort;

public interface ReservationService {
    EntityWithPagination getAllReservationWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetReservationResponse getReservationById(Long reservationId, String language);

    AddReservationResponse addReservation(AddReservationRequest request, String language);

    UpdateReservationResponse updateReservationById(Long reservationId, UpdateReservationRequest request, String language);

    void deleteReservationById(Long reservationId, String language);
}
