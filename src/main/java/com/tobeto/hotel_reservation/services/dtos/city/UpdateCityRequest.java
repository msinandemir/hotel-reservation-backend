package com.tobeto.hotel_reservation.services.dtos.city;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {
    @NotNull(message = "validation.notNull")
    @Size(min = 5, max = 10, message = "validation.size")
    private String name;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long countryId;
}
