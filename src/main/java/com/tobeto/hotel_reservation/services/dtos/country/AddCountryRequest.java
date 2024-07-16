package com.tobeto.hotel_reservation.services.dtos.country;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCountryRequest {
    @Size(min = 2, max = 20, message = "validation.size")
    private String name;
}
