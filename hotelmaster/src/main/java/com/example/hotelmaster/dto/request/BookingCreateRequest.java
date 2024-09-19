package com.example.hotelmaster.dto.request;

import com.example.hotelmaster.constant.BookingStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingCreateRequest {
    Long userId;
    String roomNumber;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    BigDecimal totalPrice;
    BookingStatus bookingStatus;
    Long serviceIds;
}
