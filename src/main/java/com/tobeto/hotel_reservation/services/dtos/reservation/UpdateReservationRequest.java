package com.tobeto.hotel_reservation.services.dtos.reservation;

import com.tobeto.hotel_reservation.entities.enums.ReservationStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull(message = "validation.notNull")
    private LocalDate checkIn;

    @NotNull(message = "validation.notNull")
    private LocalDate checkOut;

    @NotNull(message = "validation.notNull")
    private ReservationStatus status;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long userId;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long roomId;
}
