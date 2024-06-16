package com.tobeto.hotel_reservation.entities.concretes;

import com.tobeto.hotel_reservation.entities.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "photos")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Photo extends BaseEntity {
    @Column(nullable = false)
    private String url;
}
