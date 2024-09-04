package com.example.hotelmaster.dto.response;

import com.example.hotelmaster.constant.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    String bookingId;

    BigDecimal amount;

    LocalDate paymentDate;

    PaymentMethod paymentMethod;
}