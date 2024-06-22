package com.tobeto.hotel_reservation.services.dtos.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHotelResponse {
    private String name;
    private int star;
    private String phoneNumber;
    private String description;
}
