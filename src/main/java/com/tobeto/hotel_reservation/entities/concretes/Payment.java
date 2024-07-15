package com.tobeto.hotel_reservation.entities.concretes;

import com.iyzipay.model.Currency;
import com.tobeto.hotel_reservation.entities.abstracts.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "payments")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity {
    private Currency currency;
    private BigDecimal price;
    private BigDecimal paidPrice;
    private String basketId;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
