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
        enMessages.put("validation.size", "The length of the value must be between 5 and 10 characters.");
        enMessages.put("error.runtime", "An unexpected error occurred");
        enMessages.put("email.sendError", "An error occurred while sending the email.");
        enMessages.put("image.uploadError", "An error occurred while uploading the image.");
        enMessages.put("image.deleteError", "An error occurred while deleting the image.");
        enMessages.put("error.userNotFound", "User not found.");
        enMessages.put("error.userInfoNotFound", "User info not found.");
        enMessages.put("error.addressNotFound", "Address not found.");

        Map<String, String> trMessages = new HashMap<>();
        trMessages.put("exception.businessTitle", "İş Kuralı İhlali!");
        trMessages.put("exception.validationTitle", "Doğrulama Kuralı İhlali!");
        trMessages.put("exception.validationDetail", "Bir veya Daha Fazla Doğrulama Hatası!");
        trMessages.put("validation.size", "Değerin uzunluğu en az 5, en fazla 10 karakter olmalıdır.");
        trMessages.put("error.runtime", "Beklenmeyen bir hata oluştu");
        trMessages.put("email.sendError", "E-posta gönderilirken bir hata oluştu.");
        trMessages.put("image.uploadError", "Resim yüklenirken bir hata oluştu.");
        trMessages.put("image.deleteError", "Resim silinirken bir hata oluştu.");
        trMessages.put("error.userNotFound", "Kullanıcı bulunamadı.");
        trMessages.put("error.userInfoNotFound", "Kullanıcı bilgisi bulunamadı.");
        trMessages.put("error.addressNotFound", "Adres bulunamadı.");

        messages.put("en", enMessages);
        messages.put("tr", trMessages);
    }

    public static String getMessage(String lang, String code) {
        return messages.getOrDefault(lang, messages.get("en")).getOrDefault(code, code);
    }
}
