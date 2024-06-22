package com.tobeto.hotel_reservation.services.dtos.roomInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomInfoResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean computer;
    private boolean jacuzzi;
    private boolean tv;
    private boolean wifi;
    private boolean balcony;
    private boolean centralHeating;
    private boolean airConditioner;
    private boolean workDesk;
    private boolean nonSmoking;
    private Long roomId;
}
