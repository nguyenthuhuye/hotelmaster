package com.example.hotelmaster.dto.request;

import com.example.hotelmaster.constant.RoomType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypesRequest {
    RoomType roomType;
    String description;
    BigDecimal price;
    //Giá mỗi đêm
}
