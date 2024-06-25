package com.tobeto.hotel_reservation.services.dtos.user;

import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "validation.NotNull")
    @Size(min = 5, max = 10, message = "validation.size")
    private String email;
    @NotNull(message = "validation.NotNull")
    @Size(min = 5, max = 10, message = "validation.size")
    private String password;
    @NotNull(message = "validation.NotNull")
    @Size(min = 3, max = 10, message = "validation.size")
    private String firstName;
    @NotNull(message = "validation.NotNull")
    @Size(min = 2, max = 10, message = "validation.size")
    private String lastName;
}
