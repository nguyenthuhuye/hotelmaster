package com.example.hotelmaster.mapper;

import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.entity.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMaper {
    Room toRoom(RoomRequest request);
}
