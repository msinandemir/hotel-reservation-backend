package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Reservation;
import com.tobeto.hotel_reservation.services.dtos.reservation.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "user.id", target = "userId")
    GetReservationResponse getResponseFromReservation(Reservation reservation);

    @Mapping(target = "room.id", source = "roomId")
    @Mapping(target = "user.id", source = "userId")
    Reservation reservationFromAddRequest(AddReservationRequest request);

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "user.id", target = "userId")
    AddReservationResponse addResponseFromReservation(Reservation reservation);

    @Mapping(target = "room.id", source = "roomId")
    @Mapping(target = "user.id", source = "userId")
    Reservation reservationFromUpdateRequest(UpdateReservationRequest request);

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "user.id", target = "userId")
    UpdateReservationResponse updateResponseFromReservation(Reservation reservation);

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "user.id", target = "userId")
    ChangeReservationStatusResponse changeStatusResponseFromReservation(Reservation reservation);
}
