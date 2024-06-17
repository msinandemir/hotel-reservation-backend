package com.tobeto.hotel_reservation.entities.concretes;

import com.tobeto.hotel_reservation.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "hotels")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int star;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Photo> photos;

    @OneToOne(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private HotelInfo hotelInfo;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms;
}
