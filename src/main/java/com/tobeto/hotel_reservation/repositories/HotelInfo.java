package com.tobeto.hotel_reservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelInfo extends JpaRepository<HotelInfo, Long> {
}
