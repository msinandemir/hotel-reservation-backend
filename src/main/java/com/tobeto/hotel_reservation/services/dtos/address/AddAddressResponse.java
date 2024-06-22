package com.tobeto.hotel_reservation.services.dtos.address;

import com.tobeto.hotel_reservation.entities.concretes.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressResponse {
    private String title;
    private String addressLine;
    private City city;

}
