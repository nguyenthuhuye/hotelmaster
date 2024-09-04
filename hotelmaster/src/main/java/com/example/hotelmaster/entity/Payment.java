package com.example.hotelmaster.entity;

import com.example.hotelmaster.constant.PaymentMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String bookingId;

    BigDecimal amount;

    LocalDate paymentDate;

    PaymentMethod paymentMethod;

//    @OneToOne(mappedBy = "bookingId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    Set<Booking> bookings = new HashSet<>();
}
