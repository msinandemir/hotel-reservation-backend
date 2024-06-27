package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.HotelInfoService;
import com.tobeto.hotel_reservation.services.dtos.hotelInfo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hotelInfos")
@RequiredArgsConstructor
public class HotelInfoController {
    private HotelInfoService hotelInfoService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllHotelInfosWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(hotelInfoService.getAllHotelInfosWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{hotelInfoId}")
    ResponseEntity<GetHotelInfoResponse> getHotelInfoById(@PathVariable Long hotelInfoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelInfoService.getHotelInfoById(hotelInfoId, lang));
    }

    @PostMapping
    ResponseEntity<AddHotelInfoResponse> addHotelInfo(@RequestBody AddHotelInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(hotelInfoService.addHotelInfo(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{hotelInfoId}")
    ResponseEntity<UpdateHotelInfoResponse> updateHotelInfoById(@PathVariable Long hotelInfoId, @RequestBody UpdateHotelInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelInfoService.updateHotelInfoById(hotelInfoId, request, lang));
    }

    @DeleteMapping("{hotelInfoId}")
    void deleteHotelInfoById(@PathVariable Long hotelInfoId, @RequestHeader(defaultValue = "en") String lang) {
        hotelInfoService.deleteHotelInfoById(hotelInfoId, lang);
    }
}
