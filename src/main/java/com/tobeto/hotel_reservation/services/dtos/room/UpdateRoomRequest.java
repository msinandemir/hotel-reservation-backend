package com.tobeto.hotel_reservation.services.dtos.room;

import com.tobeto.hotel_reservation.entities.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull(message = "validation.NotNull")
    @Positive
    private int capacity;
    @NotNull(message = "validation.NotNull")
    @Positive
    private BigDecimal price;
    @NotNull(message = "validation.NotNull")
    private boolean availability;
    @NotNull(message = "validation.NotNull")
    @Positive
    private int singleBed;
    @NotNull(message = "validation.NotNull")
    @Positive
    private int doubleBed;
    @NotNull(message = "validation.NotNull")
    @Positive
    private int bunkBed;
    @NotNull(message = "validation.NotNull")
    private RoomType type;
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long hotelId;
}
