package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.services.abstracts.CountryService;
import com.tobeto.hotel_reservation.services.dtos.country.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/countries")
@RequiredArgsConstructor
public class CountryController {
    private CountryService countryService;

    @GetMapping
    ResponseEntity<List<GetCountryResponse>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("{countryId}")
    ResponseEntity<GetCountryResponse> getCountryById(@PathVariable Long countryId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(countryService.getCountryById(countryId, lang));
    }

    @PostMapping
    ResponseEntity<AddCountryResponse> addCountry(@RequestBody AddCountryRequest request) {
        return new ResponseEntity<>(countryService.addCountry(request), HttpStatus.CREATED);
    }

    @PutMapping("{countryId}")
    ResponseEntity<UpdateCountryResponse> updateCountryById(@PathVariable Long countryId, @RequestBody UpdateCountryRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(countryService.updateCountryById(countryId, request, lang));
    }

    @DeleteMapping("{countryId}")
    void deleteCountryById(@PathVariable Long countryId, @RequestHeader(defaultValue = "en") String lang) {
        countryService.deleteCountryById(countryId, lang);
    }
}
