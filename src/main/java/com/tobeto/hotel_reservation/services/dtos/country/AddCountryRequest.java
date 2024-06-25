package com.tobeto.hotel_reservation.services.dtos.country;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCountryRequest {
    @NotNull(message = "validation.NotNull")
    private String name;
}
