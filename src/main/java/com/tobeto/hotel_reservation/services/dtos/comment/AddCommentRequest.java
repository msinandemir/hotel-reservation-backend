package com.tobeto.hotel_reservation.services.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentRequest {
    private int cleanRating;
    private int locationRating;
    private int serviceRating;
    private int confortableRating;
    private int priceBalance;
    private Long userId;
}
