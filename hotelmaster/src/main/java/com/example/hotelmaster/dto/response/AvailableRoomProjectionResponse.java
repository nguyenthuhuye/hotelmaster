package com.example.hotelmaster.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AvailableRoomProjectionResponse {
    String roomNumber;
    String roomType;
    Double price;
}
