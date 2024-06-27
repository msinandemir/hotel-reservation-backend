package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.services.abstracts.CityService;
import com.tobeto.hotel_reservation.services.dtos.city.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cities")
@RequiredArgsConstructor
public class CityController {
    private CityService cityService;

    @GetMapping
    ResponseEntity<List<GetCityResponse>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("{cityId}")
    ResponseEntity<GetCityResponse> getCityById(@PathVariable Long cityId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(cityService.getCityById(cityId, lang));
    }

    @PostMapping
    ResponseEntity<AddCityResponse> addCity(@RequestBody AddCityRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(cityService.addCity(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{cityId}")
    ResponseEntity<UpdateCityResponse> updateCityById(@PathVariable Long cityId, @RequestBody UpdateCityRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(cityService.updateCityById(cityId, request, lang));
    }

    @DeleteMapping("{cityId}")
    void deleteCityById(@PathVariable Long cityId, @RequestHeader(defaultValue = "en") String lang) {
        cityService.deleteCityById(cityId, lang);
    }
}
