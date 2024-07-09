package com.tobeto.hotel_reservation.services.dtos.hotel;

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
public class UpdateHotelRequest {
    @NotNull(message = "validation.notNull")
    @Size(min = 2, max = 15, message = "validation.size")
    private String name;

    @NotNull(message = "validation.notNull")
    @Size(min = 1, max = 5, message = "validation.size")
    private int star;

    @NotNull(message = "validation.notNull")
    private String phoneNumber;

    @NotNull(message = "validation.notNull")
    @Size(min = 3, max = 255, message = "validation.size")
    private String description;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long addressId;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long userId;
}
