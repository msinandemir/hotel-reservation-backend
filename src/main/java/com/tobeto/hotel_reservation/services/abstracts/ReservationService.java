package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.entities.enums.ReservationStatus;
import com.tobeto.hotel_reservation.services.dtos.reservation.*;
import jakarta.mail.MessagingException;

import java.math.BigDecimal;

public interface ReservationService {
    EntityWithPagination getAllReservationWithPagination(PaginationRequest paginationRequest);

    GetReservationResponse getReservationById(Long reservationId, String language);

    EntityWithPagination getReservationsByUserId(Long userId, PaginationRequest paginationRequest);

    EntityWithPagination getReservationsByHotelId(Long hotelId, PaginationRequest paginationRequest);

    BigDecimal getTotalRevenueByUserId(Long userId);

    EntityWithPagination getPastReservationsByUserId(Long userId, PaginationRequest paginationRequest);

    AddReservationResponse addReservation(AddReservationRequest request, String language);

    UpdateReservationResponse updateReservationById(Long reservationId, UpdateReservationRequest request, String language);

    ChangeReservationStatusResponse changeReservationStatusById(Long reservationId, ReservationStatus status, String language);

    ChangeReservationStatusResponse confirmReservationById(Long reservationId, String language) throws MessagingException;

    ChangeReservationStatusResponse cancelReservationById(Long reservationId, String language) throws MessagingException;

    void deleteReservationById(Long reservationId, String language);
}
