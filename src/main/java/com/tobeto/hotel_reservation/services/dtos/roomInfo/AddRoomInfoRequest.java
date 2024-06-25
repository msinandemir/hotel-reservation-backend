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
    @NotNull(message = "validation.NotNull")
    private boolean computer;
    @NotNull(message = "validation.NotNull")
    private boolean jacuzzi;
    @NotNull(message = "validation.NotNull")
    private boolean tv;
    @NotNull(message = "validation.NotNull")
    private boolean wifi;
    @NotNull(message = "validation.NotNull")
    private boolean balcony;
    @NotNull(message = "validation.NotNull")
    private boolean centralHeating;
    @NotNull(message = "validation.NotNull")
    private boolean airConditioner;
    @NotNull(message = "validation.NotNull")
    private boolean workDesk;
    @NotNull(message = "validation.NotNull")
    private boolean nonSmoking;
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long roomId;
}
