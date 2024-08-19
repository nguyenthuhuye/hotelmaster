package com.example.hotelmaster.entity;

import com.example.hotelmaster.constant.BookingStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String userId;
    String roomNumber;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Integer totalPrice;
    BookingStatus bookingStatus;
}
