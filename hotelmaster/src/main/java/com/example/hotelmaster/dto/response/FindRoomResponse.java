package com.example.hotelmaster.dto.response;

import com.example.hotelmaster.constant.RoomStatus;
import com.example.hotelmaster.constant.RoomType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FindRoomResponse {
    String id;
    String roomNumber;
    String imageUrl;
    RoomStatus roomStatus;
    RoomType roomType;
}
