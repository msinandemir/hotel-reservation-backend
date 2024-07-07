package com.tobeto.hotel_reservation.services.abstracts;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface CloudinaryService {
    String saveImage(MultipartFile file, String language) throws IOException;

    void deleteImageByUrl(String url, String language) throws IOException;
}
