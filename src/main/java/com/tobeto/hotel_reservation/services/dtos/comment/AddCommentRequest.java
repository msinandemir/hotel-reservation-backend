package com.tobeto.hotel_reservation.services.dtos.comment;

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
public class AddCommentRequest {
    @NotNull(message = "validation.notNull")
    @Size(min = 1, max = 5, message = "validation.size")
    private int cleanRating;

    @NotNull(message = "validation.notNull")
    @Size(min = 1, max = 5, message = "validation.size")
    private int locationRating;

    @NotNull(message = "validation.notNull")
    @Size(min = 1, max = 5, message = "validation.size")
    private int serviceRating;

    @NotNull(message = "validation.notNull")
    @Size(min = 1, max = 5, message = "validation.size")
    private int confortableRating;

    @NotNull(message = "validation.notNull")
    @Size(min = 1, max = 5, message = "validation.size")
    private int priceBalance;

    @NotNull(message = "validation.notNull")
    @Size(min = 3, max = 255, message = "validation.size")
    private String content;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long userId;
}
