package com.example.hotelmaster.dto.request;

import com.example.hotelmaster.constant.BookingStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    String userId;
    String roomNumber;
    String userName;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Integer totalPrice;
    BookingStatus bookingStatus;
    String serviceName;
}
