package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.PhotoService;
import com.tobeto.hotel_reservation.services.dtos.photo.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/photos")
@RequiredArgsConstructor
@Validated
public class PhotoController {
    private final PhotoService photoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<EntityWithPagination> getAllPhotosWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                    @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                    @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                    @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(photoService.getAllPhotosWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{photoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetPhotoResponse> getPhotoById(@PathVariable @Valid @Positive(message = "validation.positive") Long photoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(photoService.getPhotoById(photoId, lang));
    }

    @GetMapping("hotelPhotos/{hotelId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<List<GetPhotoResponse>> getPhotosByHotelId(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(photoService.getPhotosByHotelId(hotelId, lang));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    ResponseEntity<AddPhotoResponse> addPhoto(@RequestBody @Valid AddPhotoRequest request, @RequestHeader(defaultValue = "en") String lang) throws IOException {
        return new ResponseEntity<>(photoService.addPhoto(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{photoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    ResponseEntity<UpdatePhotoResponse> updatePhotoById(@PathVariable @Valid @Positive(message = "validation.positive") Long photoId, @RequestBody @Valid UpdatePhotoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(photoService.updatePhotoById(photoId, request, lang));
    }

    @DeleteMapping("{photoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    void deletePhotoById(@PathVariable @Valid @Positive(message = "validation.positive") Long photoId, @RequestHeader(defaultValue = "en") String lang) throws IOException {
        photoService.deletePhotoById(photoId, lang);
    }
}
