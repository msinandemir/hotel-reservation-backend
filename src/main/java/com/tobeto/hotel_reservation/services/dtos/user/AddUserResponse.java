package com.tobeto.hotel_reservation.services.dtos.user;

import com.tobeto.hotel_reservation.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
