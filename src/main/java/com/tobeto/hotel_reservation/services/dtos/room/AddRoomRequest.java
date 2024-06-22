package com.tobeto.hotel_reservation.services.dtos.room;

import com.tobeto.hotel_reservation.entities.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddRoomRequest {
    private int capacity;
    private BigDecimal price;
    private boolean availability;
    private int singleBed;
    private int doubleBed;
    private int bunkBed;
    private RoomType type;
}
