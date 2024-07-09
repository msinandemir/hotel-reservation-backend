package com.tobeto.hotel_reservation.services.dtos.supportRequest;

import com.tobeto.hotel_reservation.entities.enums.SupportRequestType;
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
public class UpdateSupportRequestRequest {
    @NotNull(message = "validation.notNull")
    @Size(min = 3, max = 15, message = "validation.size")
    private String title;

    @NotNull(message = "validation.notNull")
    @Size(min = 3, max = 255, message = "validation.size")
    private String description;

    @NotNull(message = "validation.notNull")
    private SupportRequestType type;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long userId;
}
