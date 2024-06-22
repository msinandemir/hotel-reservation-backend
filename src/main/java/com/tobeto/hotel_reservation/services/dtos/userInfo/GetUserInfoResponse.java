package com.tobeto.hotel_reservation.services.dtos.userInfo;

import com.tobeto.hotel_reservation.entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfoResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String phoneNumber;
    private int age;
    private Gender gender;
    private Long userId;
    private Long addressId;
}
