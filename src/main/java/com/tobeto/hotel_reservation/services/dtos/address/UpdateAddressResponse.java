package com.tobeto.hotel_reservation.services.dtos.address;

import com.tobeto.hotel_reservation.entities.concretes.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String title;
    private String addressLine;
    private Long cityId;
}
