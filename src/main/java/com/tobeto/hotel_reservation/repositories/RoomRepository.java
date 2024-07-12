package com.tobeto.hotel_reservation.repositories;

import com.tobeto.hotel_reservation.entities.concretes.Room;
import com.tobeto.hotel_reservation.entities.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {
    @Query("SELECT r FROM Room r WHERE r.type = :type AND  r.availability = true AND r.id NOT IN (" +
            "SELECT res.room.id FROM Reservation res WHERE :checkIn < res.checkOut AND :checkOut > res.checkIn" +
            ")")
    List<Room> findAvailableRoomsByTypeAndDate(@Param("type") RoomType type,
                                               @Param("checkIn") LocalDate checkIn,
                                               @Param("checkOut") LocalDate checkOut);

    @Query("SELECT r FROM Room r " +
            "JOIN r.reservations res " +
            "WHERE res.checkOut < :today AND r.availability = false")
    List<Room> findRoomsPastCheckoutAndNotAvailable(@Param("today") LocalDate today);
}
