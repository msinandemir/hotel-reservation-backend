package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.models.ReservationCancelEmail;
import com.tobeto.hotel_reservation.core.models.ReservationConfirmEmail;
import com.tobeto.hotel_reservation.core.models.WelcomeEmail;
import com.tobeto.hotel_reservation.core.utils.MessageSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class GoogleEmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public boolean sendWelcomeEmail(WelcomeEmail welcomeEmail, String language) throws MessagingException {
        String templateName = "welcomeEmailTemplate";
        Context context = new Context();
        context.setVariable("title", MessageSource.getMessage(language, "welcomeEmail.title"));
        context.setVariable("message", MessageSource.getMessage(language, "welcomeEmail.message"));
        context.setVariable("userName", welcomeEmail.getUsername().toUpperCase(Locale.getDefault()));

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlBody = templateEngine.process(templateName, context);
        helper.setTo(welcomeEmail.getTo());
        helper.setSubject(MessageSource.getMessage(language, "welcomeEmail.title"));
        helper.setText(htmlBody, true);
        mailSender.send(message);
        return true;
    }

    public boolean sendReservationCancellationEmail(ReservationCancelEmail reservationCancelEmail, String language) throws MessagingException {
        String templateName = "reservationCancellation";
        Context context = new Context();
        context.setVariable("title", MessageSource.getMessage(language, "reservationCancelEmail.title"));
        context.setVariable("message", MessageSource.getMessage(language, "reservation.message"));
        context.setVariable("dear", MessageSource.getMessage(language, "email.dear"));
        context.setVariable("reservationDetailsTitle", "reservationCancelEmail.reservationDetailsTitle");
        context.setVariable("checkInTitle",MessageSource.getMessage(language, "reservationDetail.checkInTitle"));
        context.setVariable("checkOutTitle",MessageSource.getMessage(language, "reservationDetail.checkOutTitle"));
        context.setVariable("roomTypeTitle",MessageSource.getMessage(language, "reservationDetail.roomTypeTitle"));
        context.setVariable("hotelNameTitle",MessageSource.getMessage(language, "reservationDetail.hotelNameTitle"));
        context.setVariable("hotelPhoneNumberTitle",MessageSource.getMessage(language, "reservationDetail.hotelPhoneNumberTitle"));
        context.setVariable("footerMessage",MessageSource.getMessage(language, "reservationCancelEmail.footerMessage"));
        context.setVariable("checkIn", reservationCancelEmail.getCheckIn());
        context.setVariable("checkOut", reservationCancelEmail.getCheckOut());
        context.setVariable("roomType", reservationCancelEmail.getRoomType());
        context.setVariable("hotelName", reservationCancelEmail.getHotelName());
        context.setVariable("hotelPhoneNumber", reservationCancelEmail.getHotelPhoneNumber());

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlBody = templateEngine.process(templateName, context);
        helper.setTo(reservationCancelEmail.getTo());
        helper.setSubject(MessageSource.getMessage(language, "reservationCancelEmail.title"));
        helper.setText(htmlBody, true);
        mailSender.send(message);
        return true;
    }

    public boolean senReservationConfirmationEmail(ReservationConfirmEmail reservationConfirmEmail, String language) throws MessagingException {
        String templateName = "reservationConfirmation";
        Context context = new Context();
        context.setVariable("title", MessageSource.getMessage(language, "reservationConfirmEmail.title"));
        context.setVariable("message", MessageSource.getMessage(language, "reservationConfirmEmail.message"));
        context.setVariable("dear", MessageSource.getMessage(language, "email.dear"));
        context.setVariable("reservationDetailsTitle", "reservationCancelEmail.reservationDetailsTitle");
        context.setVariable("checkInTitle",MessageSource.getMessage(language, "reservationDetail.checkInTitle"));
        context.setVariable("checkOutTitle",MessageSource.getMessage(language, "reservationDetail.checkOutTitle"));
        context.setVariable("roomTypeTitle",MessageSource.getMessage(language, "reservationDetail.roomTypeTitle"));
        context.setVariable("hotelNameTitle",MessageSource.getMessage(language, "reservationDetail.hotelNameTitle"));
        context.setVariable("hotelPhoneNumberTitle",MessageSource.getMessage(language, "reservationDetail.hotelPhoneNumberTitle"));
        context.setVariable("footerMessage", MessageSource.getMessage(language, "reservationConfirm.footerMessage"));
        context.setVariable("checkIn", reservationConfirmEmail.getCheckIn());
        context.setVariable("checkOut", reservationConfirmEmail.getCheckOut());
        context.setVariable("roomType", reservationConfirmEmail.getRoomType());
        context.setVariable("hotelName", reservationConfirmEmail.getHotelName());
        context.setVariable("hotelPhoneNumber", reservationConfirmEmail.getHotelPhoneNumber());

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlBody = templateEngine.process(templateName, context);
        helper.setTo(reservationConfirmEmail.getTo());
        helper.setSubject(MessageSource.getMessage(language, "reservationConfirmEmail.title"));
        helper.setText(htmlBody, true);
        mailSender.send(message);
        return true;
    }
}
