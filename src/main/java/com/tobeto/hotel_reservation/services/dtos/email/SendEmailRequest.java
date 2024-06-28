package com.tobeto.hotel_reservation.services.dtos.email;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailRequest {

    @NotBlank(message = "to.not.blank")
    private String to;

    @NotBlank(message = "subject.not.blank")
    private String subject;
}
