package com.tobeto.hotel_reservation.services.dtos.auth;

import jakarta.validation.constraints.Email;
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
public class LoginRequest {
    @Email(message = "validation.email")
    private String email;

    @NotNull(message = "validation.notNull")
    @Size(min = 8, max = 16, message = "validation.size")
    private String password;
}
