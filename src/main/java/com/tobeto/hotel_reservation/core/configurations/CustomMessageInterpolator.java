package com.tobeto.hotel_reservation.core.configurations;

import com.tobeto.hotel_reservation.core.utils.MessageSource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.MessageInterpolator;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Locale;
import java.util.Objects;

@RequiredArgsConstructor
public class CustomMessageInterpolator implements MessageInterpolator {
    private final MessageInterpolator defaultInterpolator;

    @Override
    public String interpolate(String messageTemplate, Context context) {
        return interpolate(messageTemplate, context, Locale.getDefault());
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        Locale getCustomLocale = getCurrentLocale();
        String language = getCustomLocale.getLanguage();
        return MessageSource.getMessage(language, messageTemplate);
    }

    private Locale getCurrentLocale() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String lang = request.getHeader("lang");
        if (StringUtils.hasText(lang))
            return Locale.forLanguageTag(lang);
        else
            return Locale.getDefault();
    }
}
