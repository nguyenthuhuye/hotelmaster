package com.example.hotelmaster.dto.request;

import com.example.hotelmaster.constant.RoomType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FindRoomRequest {
    LocalDate checkInDate;
    LocalDate checkOutDate;
    RoomType roomType;
}
