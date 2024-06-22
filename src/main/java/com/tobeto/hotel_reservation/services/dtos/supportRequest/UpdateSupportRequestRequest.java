package com.tobeto.hotel_reservation.services.dtos.supportRequest;

import com.tobeto.hotel_reservation.entities.enums.SupportRequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSupportRequestRequest {
    private String title;
    private String description;
    private SupportRequestType type;
}
