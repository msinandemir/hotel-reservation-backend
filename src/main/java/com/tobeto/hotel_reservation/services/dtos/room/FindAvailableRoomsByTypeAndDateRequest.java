package com.tobeto.hotel_reservation.services.dtos.room;

import com.tobeto.hotel_reservation.entities.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindAvailableRoomsByTypeAndDateRequest {
    @NotNull(message = "validation.notNull")
    private RoomType type;

    @NotNull(message = "validation.notNull")
    private LocalDate checkIn;

    @NotNull(message = "validation.notNull")
    private LocalDate checkOut;
}
