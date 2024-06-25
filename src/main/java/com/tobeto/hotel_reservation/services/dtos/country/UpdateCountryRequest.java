package com.tobeto.hotel_reservation.services.dtos.country;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCountryRequest {
    @NotNull(message = "validation.NotNull")
    private String name;
}
