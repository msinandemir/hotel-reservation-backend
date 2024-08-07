package com.tobeto.hotel_reservation.services.dtos.address;

import com.tobeto.hotel_reservation.entities.concretes.City;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressRequest {
    @NotNull(message = "validation.notNull")
    @Size(min = 3, max = 15, message = "validation.size")
    private String title;

    @NotNull(message = "validation.notNull")
    @Size(min = 5, max = 255, message = "validation.size")
    private String addressLine;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long cityId;
}
