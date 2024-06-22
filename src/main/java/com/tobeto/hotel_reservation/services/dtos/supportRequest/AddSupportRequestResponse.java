package com.tobeto.hotel_reservation.services.dtos.supportRequest;

import com.tobeto.hotel_reservation.entities.enums.SupportRequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddSupportRequestResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String title;
    private String description;
    private SupportRequestType type;
    private Long userId;
}
