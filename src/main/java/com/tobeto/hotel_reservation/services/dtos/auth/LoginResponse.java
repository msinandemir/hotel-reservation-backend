package com.tobeto.hotel_reservation.services.dtos.auth;

import com.tobeto.hotel_reservation.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private Long userId;
    private Role role;
}
