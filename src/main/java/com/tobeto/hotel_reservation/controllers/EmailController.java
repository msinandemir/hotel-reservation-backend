package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.services.abstracts.EmailService;
import com.tobeto.hotel_reservation.services.dtos.email.SendEmailRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("send")
    ResponseEntity<Boolean> sendEmail(@Valid @RequestBody SendEmailRequest sendEmailRequest, @RequestHeader(defaultValue = "en") String language) {
        return ResponseEntity.ok(emailService.sendWelcomeEmail(sendEmailRequest, language));
    }

}
