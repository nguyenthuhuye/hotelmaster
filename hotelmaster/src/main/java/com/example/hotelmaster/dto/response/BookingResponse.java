package com.example.hotelmaster.dto.response;

import com.example.hotelmaster.constant.BookingStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    String roomNumber;
    String userName;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Integer totalPrice;
    BookingStatus bookingStatus;
}
