package com.tobeto.hotel_reservation.services.concretes;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class GoogleEmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public boolean sendWelcomeEmail(String to, String subject, String language) throws MessagingException {
        String templateName = "welcomeEmailTemplate";
        Context context = new Context();
        context.setVariable("title", "email title");
        context.setVariable("message", "email message");

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlBody = templateEngine.process(templateName, context);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        mailSender.send(message);
        return true;
    }
}