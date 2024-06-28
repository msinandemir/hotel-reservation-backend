package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.services.abstracts.EmailService;
import com.tobeto.hotel_reservation.services.dtos.email.SendEmailRequest;
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
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public boolean sendWelcomeEmail(SendEmailRequest sendEmailRequest, String language) {

        String templateName = "welcomeEmailTemplate";
        Context context = new Context();
        context.setVariable("title", "email title");
        context.setVariable("message", "email message");

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String htmlBody = templateEngine.process(templateName, context);

            helper.setTo(sendEmailRequest.getTo());
            helper.setSubject(sendEmailRequest.getSubject());
            helper.setText(htmlBody, true);
            mailSender.send(message);

            return true;
        } catch (RuntimeException | MessagingException e) {
            throw new BusinessException("email.sendError", language);
        }
    }
}
