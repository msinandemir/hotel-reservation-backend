package com.tobeto.hotel_reservation.entities.concretes;

import com.tobeto.hotel_reservation.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "room_info")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfo extends BaseEntity {
    private boolean computer;
    private boolean jacuzzi;
    private boolean tv;
    private boolean wifi;
    private boolean balcony;
    private boolean centralHeating;
    private boolean airConditioner;
    private boolean workDesk;
    private boolean nonSmoking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
