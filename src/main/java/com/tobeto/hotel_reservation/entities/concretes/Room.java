package com.tobeto.hotel_reservation.entities.concretes;

import com.tobeto.hotel_reservation.entities.abstracts.BaseEntity;
import com.tobeto.hotel_reservation.entities.enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "rooms")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseEntity {
    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean availability;

    @Column(nullable = false)
    private int singleBed;

    @Column(nullable = false)
    private int doubleBed;

    @Column(nullable = false)
    private int bunkBed;

    @Column(nullable = false)
    private RoomType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToOne(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RoomInfo roomInfo;
}
