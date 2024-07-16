package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.core.models.ReservationCancelEmail;
import com.tobeto.hotel_reservation.core.models.ReservationConfirmEmail;
import com.tobeto.hotel_reservation.core.models.WelcomeEmail;
import com.tobeto.hotel_reservation.entities.concretes.Reservation;
import com.tobeto.hotel_reservation.entities.concretes.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmailMapper {
    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);

    @Mapping(target = "to", source = "user.email")
    @Mapping(target = "roomType", source = "room.type")
    @Mapping(target = "hotelName", source = "room.hotel.name")
    @Mapping(target = "hotelPhoneNumber", source = "room.hotel.phoneNumber")
    ReservationCancelEmail reservationCancelEmailToUserFromReservation(Reservation reservation);

    @Mapping(target = "to", source = "email")
    @Mapping(target = "username", source = "firstName")
    WelcomeEmail welcomeEmailFromUser(User user);

    @Mapping(target = "to", source = "room.hotel.user.email")
    @Mapping(target = "roomType", source = "room.type")
    @Mapping(target = "hotelName", source = "room.hotel.name")
    @Mapping(target = "hotelPhoneNumber", source = "room.hotel.phoneNumber")
    ReservationCancelEmail reservationCancelEmailToManagerFromReservation(Reservation reservation);


    @Mapping(target = "to", source = "user.email")
    @Mapping(target = "roomType", source = "room.type")
    @Mapping(target = "hotelName", source = "room.hotel.name")
    @Mapping(target = "hotelPhoneNumber", source = "room.hotel.phoneNumber")
    ReservationConfirmEmail reservationConfirmEmailToUserFromReservation(Reservation reservation);

    @Mapping(target = "to", source = "room.hotel.user.email")
    @Mapping(target = "roomType", source = "room.type")
    @Mapping(target = "hotelName", source = "room.hotel.name")
    @Mapping(target = "hotelPhoneNumber", source = "room.hotel.phoneNumber")
    ReservationConfirmEmail reservationConfirmEmailToManagerFromReservation(Reservation reservation);
}
