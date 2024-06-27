package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.PhotoService;
import com.tobeto.hotel_reservation.services.dtos.photo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/photos")
@RequiredArgsConstructor
public class PhotoController {
    private PhotoService photoService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllPhotosWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(photoService.getAllPhotosWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{photoId}")
    ResponseEntity<GetPhotoResponse> getPhotoById(@PathVariable Long photoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(photoService.getPhotoById(photoId, lang));
    }

    @PostMapping
    ResponseEntity<AddPhotoResponse> addPhoto(@RequestBody AddPhotoRequest request, @RequestPart MultipartFile file, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(photoService.addPhoto(request, file, lang), HttpStatus.CREATED);
    }

    @PutMapping("{photoId}")
    ResponseEntity<UpdatePhotoResponse> updatePhotoById(@PathVariable Long photoId, @RequestBody UpdatePhotoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(photoService.updatePhotoById(photoId, request, lang));
    }

    @DeleteMapping("{photoId}")
    void deletePhotoById(@PathVariable Long photoId, @RequestHeader(defaultValue = "en") String lang) {
        photoService.deletePhotoById(photoId, lang);
    }
}
