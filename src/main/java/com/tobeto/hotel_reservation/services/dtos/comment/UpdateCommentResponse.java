package com.tobeto.hotel_reservation.services.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private int cleanRating;
    private int locationRating;
    private int serviceRating;
    private int confortableRating;
    private int priceBalance;
    private int overallRating;
    private String content;
    private Long userId;
}
