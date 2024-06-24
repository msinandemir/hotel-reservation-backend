package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.SupportRequest;
import com.tobeto.hotel_reservation.services.dtos.supportRequest.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupportRequestMapper {
    SupportRequestMapper INSTANCE = Mappers.getMapper(SupportRequestMapper.class);

    @Mapping(source = "user.id", target = "userId")
    GetSupportRequestResponse getResponseFromSupportRequest(SupportRequest supportRequest);

    @Mapping(target = "user.id", source = "userId")
    SupportRequest supportRequestFromAddRequest(AddSupportRequestRequest request);

    @Mapping(source = "user.id", target = "userId")
    AddSupportRequestResponse addResponseFromSupportRequest(SupportRequest supportRequest);

    @Mapping(target = "user.id", source = "userId")
    SupportRequest supportRequestFromUpdateRequest(UpdateSupportRequestRequest request);

    @Mapping(source = "user.id", target = "userId")
    UpdateSupportRequestResponse updateResponseFromSupportRequest(SupportRequest supportRequest);
}