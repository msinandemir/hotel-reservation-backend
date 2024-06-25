package com.tobeto.hotel_reservation.services.dtos.hotel;

import com.tobeto.hotel_reservation.entities.concretes.Address;
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
public class AddHotelRequest {
    @NotNull(message = "validation.NotNull")
    @Size(min = 5, max = 10, message = "validation.size")
    private String name;
    @NotNull(message = "validation.NotNull")
    @Positive
    private int star;
    @NotNull(message = "validation.NotNull")
    private String phoneNumber;
    @NotNull(message = "validation.NotNull")
    @Size(min = 5, max = 10, message = "validation.size")
    private String description;
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long addressId;
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long userId;
}
