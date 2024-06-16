package com.tobeto.hotel_reservation.entities.concretes;

import com.tobeto.hotel_reservation.entities.abstracts.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "user_info")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends BaseEntity {
    private String phoneNumber;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
