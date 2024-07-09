package com.tobeto.hotel_reservation.services.dtos.roomInfo;

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
public class AddRoomInfoRequest {
    private boolean computer;
    private boolean jacuzzi;
    private boolean tv;
    private boolean wifi;
    private boolean balcony;
    private boolean centralHeating;
    private boolean airConditioner;
    private boolean workDesk;
    private boolean nonSmoking;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long roomId;
}
