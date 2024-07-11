package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.services.abstracts.PhotoService;
import com.tobeto.hotel_reservation.services.dtos.photo.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/photos")
@RequiredArgsConstructor
@Validated
public class PhotoController {
    private final PhotoService photoService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllPhotosWithPagination(@RequestBody @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(photoService.getAllPhotosWithPagination(paginationRequest));
    }

    @GetMapping("{photoId}")
    ResponseEntity<GetPhotoResponse> getPhotoById(@PathVariable @Valid @Positive(message = "validation.positive") Long photoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(photoService.getPhotoById(photoId, lang));
    }

    @GetMapping("hotelPhotos/{hotelId}")
    ResponseEntity<List<GetPhotoResponse>> getPhotosByHotelId(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(photoService.getPhotosByHotelId(hotelId, lang));
    }

    @PostMapping
    ResponseEntity<AddPhotoResponse> addPhoto(@RequestBody @Valid AddPhotoRequest request, @RequestPart @NotNull(message = "validation.notNull") MultipartFile file, @RequestHeader(defaultValue = "en") String lang) throws IOException {
        return new ResponseEntity<>(photoService.addPhoto(request, file, lang), HttpStatus.CREATED);
    }

    @PutMapping("{photoId}")
    ResponseEntity<UpdatePhotoResponse> updatePhotoById(@PathVariable @Valid @Positive(message = "validation.positive") Long photoId, @RequestBody @Valid UpdatePhotoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(photoService.updatePhotoById(photoId, request, lang));
    }

    @DeleteMapping("{photoId}")
    void deletePhotoById(@PathVariable @Valid @Positive(message = "validation.positive") Long photoId, @RequestHeader(defaultValue = "en") String lang) throws IOException {
        photoService.deletePhotoById(photoId, lang);
    }
}
