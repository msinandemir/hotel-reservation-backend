package com.tobeto.hotel_reservation.services.abstracts;


import jakarta.mail.MessagingException;

public interface EmailService {
    boolean sendWelcomeEmail(String to, String subject, String language) throws MessagingException;
}
