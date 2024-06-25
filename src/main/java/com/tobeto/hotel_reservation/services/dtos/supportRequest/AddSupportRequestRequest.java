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
public class AddSupportRequestRequest {
    @NotNull(message = "validation.NotNull")
    @Size(min = 5, max = 10, message = "validation.size")
    private String title;
    @NotNull(message = "validation.NotNull")
    @Size(min = 5, max = 10, message = "validation.size")
    private String description;
    @NotNull(message = "validation.NotNull")
    @Size(min = 5, max = 10, message = "validation.size")
    private SupportRequestType type;
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long userId;
}
