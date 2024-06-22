package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Address;
import com.tobeto.hotel_reservation.services.dtos.address.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    GetAddressResponse getResponseFromAddress(Address address);
    Address addressFromAddRequest(AddAddressRequest request);
    AddAddressResponse addResponseFromAddress(Address address);
    Address addressFromUpdateRequest(UpdateAddressRequest request);
    UpdateAddressResponse updateResponseFromAddress(Address address);
}
