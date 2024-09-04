package com.example.hotelmaster.dto.response;

import com.example.hotelmaster.constant.RoomType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypesResponse {
    RoomType roomType;
    String description;
    BigDecimal price;
    //Giá mỗi đêm
}
