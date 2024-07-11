package com.tobeto.hotel_reservation.repositories;

import com.tobeto.hotel_reservation.entities.concretes.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByHotelId(Long hotelId);
}
