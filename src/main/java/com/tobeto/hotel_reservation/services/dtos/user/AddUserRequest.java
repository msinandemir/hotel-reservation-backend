package com.tobeto.hotel_reservation.services.dtos.user;

import jakarta.validation.constraints.Email;
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
public class AddUserRequest {
    @NotNull(message = "validation.notNull")
    @Email(message = "validation.email")
    private String email;

    @NotNull(message = "validation.notNull")
    @Size(min = 8, max = 16, message = "validation.size")
    private String password;

    @NotNull(message = "validation.notNull")
    @Size(min = 2, max = 15, message = "validation.size")
    private String firstName;

    @NotNull(message = "validation.notNull")
    @Size(min = 2, max = 15, message = "validation.size")
    private String lastName;

    @Positive(message = "validation.positive")
    private Long addressId;
}
