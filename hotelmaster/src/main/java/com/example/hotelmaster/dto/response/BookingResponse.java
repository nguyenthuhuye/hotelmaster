package com.example.hotelmaster.dto.response;

import com.example.hotelmaster.constant.BookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    long id;
    String roomNumber;
    String userName;
    Long roomId;
    Long userId;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    BigDecimal totalPrice;
    BookingStatus bookingStatus;
    String phoneNumber;
    List<Long> serviceId;
    String email;
    String adress;
    String phoneNumer;
}


