package com.tobeto.hotel_reservation.repositories;

import com.tobeto.hotel_reservation.entities.concretes.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a " +
            "JOIN a.users u " +
            "WHERE u.id = :userId")
    List<Address> getAddressesByUserId(@Param("userId") Long userId);
}
