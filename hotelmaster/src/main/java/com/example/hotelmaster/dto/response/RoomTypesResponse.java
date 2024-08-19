package com.example.hotelmaster.dto.response;

import com.example.hotelmaster.constant.RoomType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypesResponse {
    String id;
    RoomType roomType;
    String description;
    Integer capactity;
    //Sức chứa
    Integer pricePerNight;
    //Giá mỗi đêm
}
