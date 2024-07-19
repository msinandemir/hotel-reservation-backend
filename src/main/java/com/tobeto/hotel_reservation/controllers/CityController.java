package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.services.abstracts.CityService;
import com.tobeto.hotel_reservation.services.dtos.city.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cities")
@RequiredArgsConstructor
@Validated
public class CityController {
    private final CityService cityService;

    @GetMapping
    ResponseEntity<List<GetCityResponse>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("{cityId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetCityResponse> getCityById(@PathVariable @Valid @Positive(message = "validation.positive") Long cityId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(cityService.getCityById(cityId, lang));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<AddCityResponse> addCity(@RequestBody @Valid AddCityRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(cityService.addCity(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{cityId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UpdateCityResponse> updateCityById(@PathVariable @Valid @Positive(message = "validation.positive") Long cityId, @RequestBody @Valid UpdateCityRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(cityService.updateCityById(cityId, request, lang));
    }

    @DeleteMapping("{cityId}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteCityById(@PathVariable @Valid @Positive(message = "validation.positive") Long cityId, @RequestHeader(defaultValue = "en") String lang) {
        cityService.deleteCityById(cityId, lang);
    }
}
