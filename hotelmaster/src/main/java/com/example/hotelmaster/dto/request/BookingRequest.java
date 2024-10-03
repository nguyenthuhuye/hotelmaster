package com.example.hotelmaster.dto.request;

import com.example.hotelmaster.constant.BookingStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    Long userId;
    String roomNumber;
    Long roomId;
    String userName;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    BigDecimal totalPrice;
    BookingStatus bookingStatus;
    List<Long> serviceId;
    String email;
    String adress;
    String phoneNumer;
    String serviceUpdate;
}
