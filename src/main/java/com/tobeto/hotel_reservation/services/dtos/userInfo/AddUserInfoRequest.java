package com.tobeto.hotel_reservation.services.dtos.userInfo;

import com.tobeto.hotel_reservation.entities.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @Positive(message = "validation.positive")
    @Size(min = 18, max = 150, message = "validation.size")
    private int age;
    private Gender gender;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long userId;
}
