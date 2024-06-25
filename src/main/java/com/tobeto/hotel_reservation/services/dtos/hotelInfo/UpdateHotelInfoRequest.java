package com.tobeto.hotel_reservation.services.dtos.hotelInfo;

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
public class UpdateHotelInfoRequest {
    @NotNull(message = "validation.NotNull")
    private boolean openAirSwimmingPool;
    @NotNull(message = "validation.NotNull")
    private boolean nextToDoSeaShore;
    @NotNull(message = "validation.NotNull")
    private boolean electricCarCharger;
    @NotNull(message = "validation.NotNull")
    private boolean indoorSwimmingPool;
    @NotNull(message = "validation.NotNull")
    private boolean conferenceRoom;
    @NotNull(message = "validation.NotNull")
    private boolean spa;
    @NotNull(message = "validation.NotNull")
    private boolean fitness;
    @NotNull(message = "validation.NotNull")
    private boolean sauna;
    @NotNull(message = "validation.NotNull")
    private boolean massage;
    @NotNull(message = "validation.NotNull")
    private boolean hamam;
    @NotNull(message = "validation.NotNull")
    private boolean steamRoom;
    @NotNull(message = "validation.NotNull")
    private boolean beautyRoom;
    @NotNull(message = "validation.NotNull")
    private boolean petsAllowed;
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long hotelId;
}
