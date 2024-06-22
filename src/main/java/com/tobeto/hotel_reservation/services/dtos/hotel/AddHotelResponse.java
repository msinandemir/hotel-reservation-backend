package com.tobeto.hotel_reservation.services.dtos.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddHotelResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    private int star;
    private String phoneNumber;
    private String description;
    private Long addressId;
    private Long userId;
}
