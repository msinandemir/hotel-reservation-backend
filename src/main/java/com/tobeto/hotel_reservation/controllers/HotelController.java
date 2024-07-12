package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.services.abstracts.HotelService;
import com.tobeto.hotel_reservation.services.dtos.hotel.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hotels")
@RequiredArgsConstructor
@Validated
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllHotelsWithPagination(@RequestBody @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(hotelService.getAllHotelsWithPagination(paginationRequest));
    }

    @GetMapping("{hotelId}")
    ResponseEntity<GetHotelResponse> getHotelById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelService.getHotelById(hotelId, lang));
    }

    @GetMapping("filterHotelByStarAndCityName")
    ResponseEntity<EntityWithPagination> getFilteredHotelsByStarAndCityNameWithPagination(@RequestParam(required = false) Integer star,
                                                                                          @RequestParam(required = false) String cityName,
                                                                                          @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                                                          @RequestParam(required = false, defaultValue = "16") int pageSize,
                                                                                          @RequestParam(required = false, defaultValue = "ASC") Sort.Direction direction,
                                                                                          @RequestParam(required = false, defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(hotelService.getFilteredHotelsByStarAndCityNameWithPagination(star, cityName, pageNumber, pageSize, direction, sortBy));
    }

    @PostMapping
    ResponseEntity<AddHotelResponse> addHotel(@RequestBody @Valid AddHotelRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(hotelService.addHotel(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{hotelId}")
    ResponseEntity<UpdateHotelResponse> updateHotelById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId, @RequestBody @Valid UpdateHotelRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelService.updateHotelById(hotelId, request, lang));
    }

    @DeleteMapping("{hotelId}")
    void deleteHotelById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId, @RequestHeader(defaultValue = "en") String lang) {
        hotelService.deleteHotelById(hotelId, lang);
    }
}
