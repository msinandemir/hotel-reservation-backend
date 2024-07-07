package com.tobeto.hotel_reservation.services.dtos.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReservationRequest {
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Long userId;
    private Long roomId;
}
