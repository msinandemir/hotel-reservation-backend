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
public class AddHotelInfoRequest {
    private boolean openAirSwimmingPool;
    private boolean nextToDoSeaShore;
    private boolean electricCarCharger;
    private boolean indoorSwimmingPool;
    private boolean conferenceRoom;
    private boolean spa;
    private boolean fitness;
    private boolean sauna;
    private boolean massage;
    private boolean hamam;
    private boolean steamRoom;
    private boolean beautyRoom;
    private boolean petsAllowed;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long hotelId;
}
