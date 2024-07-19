package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.services.abstracts.CountryService;
import com.tobeto.hotel_reservation.services.dtos.country.*;
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
@RequestMapping("api/v1/countries")
@RequiredArgsConstructor
@Validated
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<List<GetCountryResponse>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("{countryId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetCountryResponse> getCountryById(@PathVariable @Valid @Positive(message = "validation.positive") Long countryId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(countryService.getCountryById(countryId, lang));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<AddCountryResponse> addCountry(@RequestBody @Valid AddCountryRequest request) {
        return new ResponseEntity<>(countryService.addCountry(request), HttpStatus.CREATED);
    }

    @PutMapping("{countryId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UpdateCountryResponse> updateCountryById(@PathVariable @Valid @Positive(message = "validation.positive") Long countryId, @RequestBody @Valid UpdateCountryRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(countryService.updateCountryById(countryId, request, lang));
    }

    @DeleteMapping("{countryId}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteCountryById(@PathVariable @Valid @Positive(message = "validation.positive") Long countryId, @RequestHeader(defaultValue = "en") String lang) {
        countryService.deleteCountryById(countryId, lang);
    }
}
