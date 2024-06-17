package com.tobeto.hotel_reservation.entities.concretes;

import com.tobeto.hotel_reservation.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "hotel_info")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelInfo extends BaseEntity {
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
