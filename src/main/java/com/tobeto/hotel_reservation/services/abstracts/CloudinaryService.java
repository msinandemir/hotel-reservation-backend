package com.tobeto.hotel_reservation.services.abstracts;

import org.springframework.web.multipart.MultipartFile;


public interface CloudinaryService {
    String saveImage(MultipartFile file, String language);

    void deleteImageByUrl(String url, String language);
}
