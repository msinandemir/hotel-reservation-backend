package com.tobeto.hotel_reservation.repositories;

import com.tobeto.hotel_reservation.entities.concretes.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT r FROM Reservation r WHERE r.room.hotel.id = :hotelId")
    Page<Reservation> findByHotelId(@Param("hotelId") Long hotelId, Pageable pageable);

    @Query("SELECT SUM(r.totalPrice) FROM Reservation r " +
            "JOIN r.room ro " +
            "JOIN ro.hotel h " +
            "JOIN h.user u " +
            "WHERE r.status = com.tobeto.hotel_reservation.entities.enums.ReservationStatus.CONFIRMED " +
            "AND u.id = :userId")
    BigDecimal findTotalRevenueByOwnerId(@Param("userId") Long userId);
}
