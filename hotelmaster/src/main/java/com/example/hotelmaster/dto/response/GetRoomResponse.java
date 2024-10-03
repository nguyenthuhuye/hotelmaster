package com.example.hotelmaster.dto.response;

import com.example.hotelmaster.constant.RoomStatus;
import com.example.hotelmaster.constant.RoomType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetRoomResponse {
    Long id;
    String roomNumber;
    String imageUrl;
    RoomStatus roomStatus;
    RoomType roomType; // Hoặc RoomType nếu bạn có lớp riêng
    BigDecimal price; // Giá tiền từ RoomTypes
}
