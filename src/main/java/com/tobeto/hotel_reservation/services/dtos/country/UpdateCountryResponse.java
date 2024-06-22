package com.tobeto.hotel_reservation.services.dtos.country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCountryResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
}
