package com.example.hotelmaster.mapper;

import com.example.hotelmaster.dto.request.RoomTypesRequest;
import com.example.hotelmaster.entity.RoomTypes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomTypesMapper {
    RoomTypes toRoomTypes(RoomTypesRequest request );
}
