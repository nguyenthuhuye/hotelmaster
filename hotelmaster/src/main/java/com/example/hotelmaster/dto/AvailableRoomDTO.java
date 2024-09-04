package com.example.hotelmaster.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AvailableRoomDTO {
    Long roomId;
    String roomNumber;
    BigDecimal price;
}
