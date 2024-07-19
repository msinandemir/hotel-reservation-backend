package com.tobeto.hotel_reservation.repositories;

import com.tobeto.hotel_reservation.entities.concretes.HotelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelInfoRepository extends JpaRepository<HotelInfo, Long> {
   Optional<HotelInfo> findByHotelId(Long hotelId);
}
