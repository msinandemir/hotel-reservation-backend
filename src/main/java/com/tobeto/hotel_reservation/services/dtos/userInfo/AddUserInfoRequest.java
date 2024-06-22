package com.tobeto.hotel_reservation.services.dtos.userInfo;

import com.tobeto.hotel_reservation.entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserInfoRequest {
    private String phoneNumber;
    private int age;
    private Gender gender;
}
