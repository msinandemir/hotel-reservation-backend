package com.tobeto.hotel_reservation.entities.concretes;

import com.tobeto.hotel_reservation.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "cities")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Address> addresses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    Country country;
}
