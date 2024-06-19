package com.tobeto.hotel_reservation.core.configurations;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfiguration {
    @Bean
    Cloudinary cloudinary() {
        Map<String, String> cloudinaryConfigs = new HashMap<>();
        cloudinaryConfigs.put("cloud_name", "duj3qvlgb");
        cloudinaryConfigs.put("api_key", "526369338779653");
        cloudinaryConfigs.put("api_secret", "s8eYor36kR0b3TtrvCipDYiO0nI");
        return new Cloudinary(cloudinaryConfigs);
    }
}
