package com.tobeto.hotel_reservation.core.models;

import com.tobeto.hotel_reservation.entities.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationConfirmEmail {
    private String to;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private RoomType roomType;
    private String hotelName;
    private String hotelPhoneNumber;
}
