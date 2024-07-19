package com.tobeto.hotel_reservation.repositories;

import com.tobeto.hotel_reservation.entities.concretes.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {
    Optional<RoomInfo> findByRoomId(Long roomId);
}
