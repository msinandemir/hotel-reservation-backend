package com.tobeto.hotel_reservation.entities.concretes;

import com.tobeto.hotel_reservation.entities.abstracts.BaseEntity;
import com.tobeto.hotel_reservation.entities.enums.RoomType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}
