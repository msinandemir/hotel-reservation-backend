package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.HotelService;
import com.tobeto.hotel_reservation.services.dtos.hotel.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {
    private HotelService hotelService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllHotelsWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(hotelService.getAllHotelsWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{hotelId}")
    ResponseEntity<GetHotelResponse> getHotelById(@PathVariable Long hotelId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelService.getHotelById(hotelId, lang));
    }

    @PostMapping
    ResponseEntity<AddHotelResponse> addHotel(@RequestBody AddHotelRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(hotelService.addHotel(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{hotelId}")
    ResponseEntity<UpdateHotelResponse> updateHotelById(@PathVariable Long hotelId, @RequestBody UpdateHotelRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelService.updateHotelById(hotelId, request, lang));
    }

    @DeleteMapping("{hotelId}")
    void deleteHotelById(@PathVariable Long hotelId, @RequestHeader(defaultValue = "en") String lang) {
        hotelService.deleteHotelById(hotelId, lang);
    }
}
