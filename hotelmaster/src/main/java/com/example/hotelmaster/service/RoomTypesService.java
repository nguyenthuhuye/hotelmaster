package com.example.hotelmaster.service;


import com.example.hotelmaster.dto.request.RoomTypesRequest;
import com.example.hotelmaster.entity.RoomTypes;
import com.example.hotelmaster.entity.User;
import com.example.hotelmaster.mapper.RoomTypesMapper;
import com.example.hotelmaster.repository.RoomTypesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoomTypesService {
    RoomTypesRepository roomTypesRepository;
    RoomTypesMapper roomTypesMapper;

    public RoomTypes createRoomtypes(RoomTypesRequest request) {

        RoomTypes roomTypes = roomTypesMapper.toRoomTypes(request);

        return roomTypesRepository.save(roomTypes);
    }


    public List<RoomTypes> getAllRoomTypes() {
        return roomTypesRepository.findAll();
    }

    public RoomTypes getRoomTypes(String id) {
        return roomTypesRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Room types not found"));
    }

    public RoomTypes updateRoomTypes(String id, RoomTypesRequest request) {
        RoomTypes roomTypes = getRoomTypes(id);
        roomTypes = roomTypesMapper.toRoomTypes(request);
        return roomTypesRepository.save(roomTypes);
    }


    public void deleteRoomTypes(String id) {
        roomTypesRepository.deleteById(id);
    }
}
