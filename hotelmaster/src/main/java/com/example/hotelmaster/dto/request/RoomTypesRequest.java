package com.example.hotelmaster.dto.request;

import com.example.hotelmaster.constant.RoomType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypesRequest {
    RoomType roomType;
    String description;
    Integer capactity;
    //Sức chứa
    Integer pricePerNight;
    //Giá mỗi đêm
}
