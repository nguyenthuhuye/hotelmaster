package com.example.hotelmaster.dto.request;

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
public class PaymentRequest {
    String bookingId;

    BigDecimal amount;

    LocalDate paymentDate;

    PaymentMethod paymentMethod;
}
