package com.tobeto.hotel_reservation.services.dtos.hotelInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddHotelInfoResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
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
    private Long hotelId;
}
