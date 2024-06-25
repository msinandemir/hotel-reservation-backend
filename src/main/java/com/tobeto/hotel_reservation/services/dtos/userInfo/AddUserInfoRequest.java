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
    @NotNull(message = "validation.NotNull")
    @Size(min = 5, max = 10, message = "validation.size")
    private String phoneNumber;
    @NotNull(message = "validation.NotNull")
    @Positive
    private int age;
    @NotNull(message = "validation.NotNull")
    private Gender gender;
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long userId;
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long addressId;
}
