package com.tobeto.hotel_reservation.services.dtos.comment;

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
public class UpdateCommentRequest {
    @NotNull(message = "validation.NotNull")
    @Positive
    private int cleanRating;
    @NotNull(message = "validation.NotNull")
    @Positive
    private int locationRating;
    @NotNull(message = "validation.NotNull")
    @Positive
    private int serviceRating;
    @NotNull(message = "validation.NotNull")
    @Positive
    private int confortableRating;
    @NotNull(message = "validation.NotNull")
    @Positive
    private int priceBalance;
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long userId;
}
