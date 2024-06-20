package com.tobeto.hotel_reservation.services.dtos.user;

import com.tobeto.hotel_reservation.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponse {
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
