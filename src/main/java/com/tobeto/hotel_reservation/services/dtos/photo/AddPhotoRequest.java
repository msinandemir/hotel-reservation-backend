package com.tobeto.hotel_reservation.services.dtos.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPhotoRequest {
    private Long hotelId;
}
