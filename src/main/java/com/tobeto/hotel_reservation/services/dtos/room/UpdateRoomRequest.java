package com.tobeto.hotel_reservation.services.dtos.room;

import com.tobeto.hotel_reservation.entities.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomRequest {
    @NotNull(message = "validation.notNull")
    @PositiveOrZero(message = "validation.positiveOrZero")
    private int capacity;

    @NotNull(message = "validation.notNull")
    @PositiveOrZero(message = "validation.positiveOrZero")
    private BigDecimal price;

    @NotNull(message = "validation.notNull")
    private boolean availability;

    @NotNull(message = "validation.notNull")
    @PositiveOrZero(message = "validation.positiveOrZero")
    private int singleBed;

    @NotNull(message = "validation.notNull")
    @PositiveOrZero(message = "validation.positiveOrZero")
    private int doubleBed;

    @NotNull(message = "validation.notNull")
    @PositiveOrZero(message = "validation.positiveOrZero")
    private int bunkBed;

    @NotNull(message = "validation.notNull")
    private RoomType type;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long hotelId;
}
