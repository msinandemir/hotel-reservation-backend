package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.HotelInfoService;
import com.tobeto.hotel_reservation.services.dtos.hotelInfo.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hotelInfos")
@RequiredArgsConstructor
@Validated
public class HotelInfoController {
    private final HotelInfoService hotelInfoService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllHotelInfosWithPagination(@RequestParam @Valid @Positive(message = "validation.positive") int pageNumber, @RequestParam @Valid @Positive(message = "validation.positive") int pageSize) {
        return ResponseEntity.ok(hotelInfoService.getAllHotelInfosWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{hotelInfoId}")
    ResponseEntity<GetHotelInfoResponse> getHotelInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelInfoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelInfoService.getHotelInfoById(hotelInfoId, lang));
    }

    @PostMapping
    ResponseEntity<AddHotelInfoResponse> addHotelInfo(@RequestBody @Valid AddHotelInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(hotelInfoService.addHotelInfo(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{hotelInfoId}")
    ResponseEntity<UpdateHotelInfoResponse> updateHotelInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelInfoId, @RequestBody @Valid UpdateHotelInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelInfoService.updateHotelInfoById(hotelInfoId, request, lang));
    }

    @DeleteMapping("{hotelInfoId}")
    void deleteHotelInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelInfoId, @RequestHeader(defaultValue = "en") String lang) {
        hotelInfoService.deleteHotelInfoById(hotelInfoId, lang);
    }
}
