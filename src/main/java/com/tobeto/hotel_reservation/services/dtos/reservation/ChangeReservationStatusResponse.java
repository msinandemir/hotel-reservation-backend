package com.tobeto.hotel_reservation.services.dtos.reservation;

import com.tobeto.hotel_reservation.entities.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeReservationStatusResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private ReservationStatus status;
    private Long userId;
    private Long roomId;
}
