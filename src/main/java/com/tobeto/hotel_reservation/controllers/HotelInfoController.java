package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.HotelInfoService;
import com.tobeto.hotel_reservation.services.dtos.hotelInfo.*;
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
@RequestMapping("api/v1/hotelInfos")
@RequiredArgsConstructor
@Validated
public class HotelInfoController {
    private final HotelInfoService hotelInfoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<EntityWithPagination> getAllHotelInfosWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                        @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                        @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                        @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(hotelInfoService.getAllHotelInfosWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{hotelInfoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetHotelInfoResponse> getHotelInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelInfoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelInfoService.getHotelInfoById(hotelInfoId, lang));
    }

    @GetMapping("getHotelInfoByHotelId/{hotelId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetHotelInfoResponse> getHotelInfoByHotelId(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId,
                                                               @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelInfoService.getHotelInfoByHotelId(hotelId, lang));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    ResponseEntity<AddHotelInfoResponse> addHotelInfo(@RequestBody @Valid AddHotelInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(hotelInfoService.addHotelInfo(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{hotelInfoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    ResponseEntity<UpdateHotelInfoResponse> updateHotelInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelInfoId, @RequestBody @Valid UpdateHotelInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(hotelInfoService.updateHotelInfoById(hotelInfoId, request, lang));
    }

    @DeleteMapping("{hotelInfoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    void deleteHotelInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelInfoId, @RequestHeader(defaultValue = "en") String lang) {
        hotelInfoService.deleteHotelInfoById(hotelInfoId, lang);
    }
}
