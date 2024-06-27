package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.services.abstracts.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/clodinaries")
@RequiredArgsConstructor
public class CloudinaryController {
    private CloudinaryService cloudinaryService;

    @PostMapping
    ResponseEntity<String> saveImage(@RequestPart MultipartFile file, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(cloudinaryService.saveImage(file, lang), HttpStatus.CREATED);
    }

    @DeleteMapping("{url}")
    void deleteImageByUrl(@PathVariable String url, @RequestHeader(defaultValue = "en") String lang) {
        cloudinaryService.deleteImageByUrl(url, lang);
    }

}
