package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Address;
import com.tobeto.hotel_reservation.services.dtos.address.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "city.id", target = "cityId")
    GetAddressResponse getResponseFromAddress(Address address);

    @Mapping(target = "city.id", source = "cityId")
    Address addressFromAddRequest(AddAddressRequest request);

    @Mapping(source = "city.id", target = "cityId")
    AddAddressResponse addResponseFromAddress(Address address);

    @Mapping(target = "city.id", source = "cityId")
    Address addressFromUpdateRequest(UpdateAddressRequest request);

    @Mapping(source = "city.id", target = "cityId")
    UpdateAddressResponse updateResponseFromAddress(Address address);
}
