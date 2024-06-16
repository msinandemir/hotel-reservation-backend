package com.tobeto.hotel_reservation.entities.concretes;

import com.tobeto.hotel_reservation.entities.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "support_requests")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupportRequest extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;
}
