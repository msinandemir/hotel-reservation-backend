package com.tobeto.hotel_reservation.repositories;

import com.tobeto.hotel_reservation.entities.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
