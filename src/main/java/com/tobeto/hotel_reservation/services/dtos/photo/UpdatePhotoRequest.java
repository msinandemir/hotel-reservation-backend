package com.tobeto.hotel_reservation.services.dtos.photo;

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
public class UpdatePhotoRequest {
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long hotelId;
}
