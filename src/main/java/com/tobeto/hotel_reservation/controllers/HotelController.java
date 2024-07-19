package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.HotelService;
import com.tobeto.hotel_reservation.services.dtos.hotel.*;
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

@RestController
@RequestMapping("api/v1/hotels")
@RequiredArgsConstructor
@Validated
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<EntityWithPagination> getAllHotelsWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                    @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                    @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                    @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(hotelService.getAllHotelsWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{hotelId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetHotelResponse> getHotelById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelService.getHotelById(hotelId, lang));
    }

    @GetMapping("filterHotelByStarAndCityName")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<EntityWithPagination> getFilteredHotelsByStarAndCityNameWithPagination(@RequestParam(required = false) Integer star,
                                                                                          @RequestParam(required = false) String cityName,
                                                                                          @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                                                          @RequestParam(required = false, defaultValue = "16") int pageSize,
                                                                                          @RequestParam(required = false, defaultValue = "ASC") Sort.Direction direction,
                                                                                          @RequestParam(required = false, defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(hotelService.getFilteredHotelsByStarAndCityNameWithPagination(star, cityName, pageNumber, pageSize, direction, sortBy));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    ResponseEntity<AddHotelResponse> addHotel(@RequestBody @Valid AddHotelRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(hotelService.addHotel(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{hotelId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    ResponseEntity<UpdateHotelResponse> updateHotelById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId, @RequestBody @Valid UpdateHotelRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelService.updateHotelById(hotelId, request, lang));
    }

    @DeleteMapping("{hotelId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    void deleteHotelById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId, @RequestHeader(defaultValue = "en") String lang) {
        hotelService.deleteHotelById(hotelId, lang);
    }
}
