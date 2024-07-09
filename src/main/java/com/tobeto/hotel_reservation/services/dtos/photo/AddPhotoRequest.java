package com.tobeto.hotel_reservation.services.dtos.photo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPhotoRequest {
    @NotNull(message = "validation.notNull")
    private MultipartFile file;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long hotelId;
}
