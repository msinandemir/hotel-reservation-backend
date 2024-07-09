package com.tobeto.hotel_reservation.core.utils;

import java.util.HashMap;
import java.util.Map;

public final class MessageSource {
    private MessageSource() {
    }

    private static final Map<String, Map<String, String>> messages = new HashMap<>();

    static {
        Map<String, String> enMessages = new HashMap<>();
        enMessages.put("exception.businessTitle", "Business Rule Violation!");
        enMessages.put("exception.validationTitle", "Validation Rule Violation!");
        enMessages.put("exception.validationDetail", "One or More Validation Error(s)!");
        enMessages.put("exception.messagingTitle", "Messaging Rule Violation!");
        enMessages.put("exception.ioTitle", "IO Rule Violation!");
        enMessages.put("exception.ioDetail", "An error occurred during the input/output operation.");
        enMessages.put("validation.size", "The length of the value must be between 5 and 10 characters.");
        enMessages.put("validation.notNull", "This field cannot be null.");
        enMessages.put("validation.positive", "This field must be a positive value.");
        enMessages.put("validation.email", "Please enter a valid email address.");
        enMessages.put("error.runtime", "An unexpected error occurred");
        enMessages.put("email.sendError", "An error occurred while sending the email.");
        enMessages.put("error.userNotFound", "User not found.");
        enMessages.put("error.userInfoNotFound", "User info not found.");
        enMessages.put("error.addressNotFound", "Address not found.");
        enMessages.put("error.supportRequestNotFound", "Support request not found.");
        enMessages.put("error.roomNotFound", "Room not found.");
        enMessages.put("error.hotelNotFound", "Hotel not found.");
        enMessages.put("error.roomInfoNotFound", "Room info not found.");
        enMessages.put("error.hotelInfoNotFound", "Hotel info not found.");
        enMessages.put("error.photoNotFound", "Photo not found.");
        enMessages.put("error.commentNotFound", "Comment not found.");
        enMessages.put("error.commentReplyNotFound", "Comment reply not found.");
        enMessages.put("error.countryNotFound", "Country not found.");
        enMessages.put("error.cityNotFound", "City not found.");
        enMessages.put("error.reservationNotFound", "Reservation not found.");
        enMessages.put("error.emailExists", "There is a user registered with this email.");
        enMessages.put("error.availableRoom", "No available rooms found. Please select a different date or room type.");

        Map<String, String> trMessages = new HashMap<>();
        trMessages.put("exception.businessTitle", "İş Kuralı İhlali!");
        trMessages.put("exception.validationTitle", "Doğrulama Kuralı İhlali!");
        trMessages.put("exception.validationDetail", "Bir veya Daha Fazla Doğrulama Hatası!");
        trMessages.put("exception.messagingTitle", "Mesaj Kuralı İhlali!");
        trMessages.put("exception.ioTitle", "Dosya İşlem İhlali");
        trMessages.put("exception.ioDetail", "Dosya işlemi sırasında bir hata oluştu.");
        trMessages.put("validation.size", "Değerin uzunluğu en az 5, en fazla 10 karakter olmalıdır.");
        trMessages.put("validation.notNull", "Bu alan boş bırakılamaz.");
        trMessages.put("validation.positive", "Bu alan pozitif bir değer olmalıdır.");
        trMessages.put("validation.email", "Geçerli bir e-posta adresi giriniz.");
        trMessages.put("error.runtime", "Beklenmeyen bir hata oluştu");
        trMessages.put("email.sendError", "E-posta gönderilirken bir hata oluştu.");
        trMessages.put("error.userNotFound", "Kullanıcı bulunamadı.");
        trMessages.put("error.userInfoNotFound", "Kullanıcı bilgisi bulunamadı.");
        trMessages.put("error.addressNotFound", "Adres bulunamadı.");
        trMessages.put("error.supportRequestNotFound", "Destek isteği bulunamadı.");
        trMessages.put("error.roomNotFound", "Oda bulunamadı.");
        trMessages.put("error.hotelNotFound", "Otel bulunamadı.");
        trMessages.put("error.roomInfoNotFound", "Oda bilgisi bulunamadı.");
        trMessages.put("error.hotelInfoNotFound", "Otel bilgisi bulunamadı.");
        trMessages.put("error.photoNotFound", "Fotoğraf bulunamadı.");
        trMessages.put("error.commentNotFound", "Yorum bulunamadı.");
        trMessages.put("error.commentReplyNotFound", "Yorum yanıtı bulunamadı.");
        trMessages.put("error.countryNotFound", "Ülke bulunamadı.");
        trMessages.put("error.cityNotFound", "Şehir bulunamadı.");
        trMessages.put("error.reservationNotFound", "Rezervasyon bulunamadı.");
        trMessages.put("error.emailExists", "Bu e-posta ile kayıtlı kullanıcı var.");
        trMessages.put("error.availableRoom", "Müsait oda bulunamadı. Lütfen başka bir tarih veya oda tipi seçin.");

        messages.put("en", enMessages);
        messages.put("tr", trMessages);
    }

    public static String getMessage(String lang, String code) {
        return messages.getOrDefault(lang, messages.get("en")).getOrDefault(code, code);
    }
}
