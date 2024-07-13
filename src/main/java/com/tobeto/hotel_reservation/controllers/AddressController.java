package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.AddressService;
import com.tobeto.hotel_reservation.services.dtos.address.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/addresses")
@RequiredArgsConstructor
@Validated
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllAddressWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                     @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                     @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                     @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(addressService.getAllAddressWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{addressId}")
    ResponseEntity<GetAddressResponse> getAddressById(@PathVariable @Valid @Positive(message = "validation.positive") Long addressId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(addressService.getAddressById(addressId, lang));
    }

    @PostMapping
    ResponseEntity<AddAddressResponse> addAddress(@RequestBody @Valid AddAddressRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(addressService.addAddress(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{addressId}")
    ResponseEntity<UpdateAddressResponse> updateAddressById(@PathVariable @Valid @Positive(message = "validation.positive") Long addressId, @RequestBody @Valid UpdateAddressRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(addressService.updateAddressById(addressId, request, lang));
    }

    @DeleteMapping("{addressId}")
    void deleteAddressById(@PathVariable @Valid @Positive(message = "validation.positive") Long addressId, @RequestHeader(defaultValue = "en") String lang) {
        addressService.deleteAddressById(addressId, lang);
    }
}
