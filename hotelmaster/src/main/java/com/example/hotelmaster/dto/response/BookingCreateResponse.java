package com.example.hotelmaster.dto.response;

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
public class BookingCreateResponse {
    String userName;
    String roomNumber;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    BigDecimal totalPrice;
    BookingStatus bookingStatus;
    Long serviceIds;
}
