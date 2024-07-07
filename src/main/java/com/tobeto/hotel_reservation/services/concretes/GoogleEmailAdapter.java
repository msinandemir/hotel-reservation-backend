package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.services.abstracts.EmailGateway;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleEmailAdapter implements EmailGateway {
    private final GoogleEmailService googleEmailService;

    @Override
    public boolean sendWelcomeEmail(String to, String subject, String language) throws MessagingException {
        return googleEmailService.sendWelcomeEmail(to, subject, language);
    }
}
