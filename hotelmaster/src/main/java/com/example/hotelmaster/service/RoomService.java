package com.example.hotelmaster.service;

import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.dto.request.RoomTypesRequest;
import com.example.hotelmaster.entity.Room;
import com.example.hotelmaster.entity.RoomTypes;
import com.example.hotelmaster.mapper.RoomMaper;
import com.example.hotelmaster.repository.RoomRepository;
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
public class RoomService {
    RoomRepository roomRepository;
    RoomMaper roomMaper;

    public Room createRoom(RoomRequest request) {

        Room room = roomMaper.toRoom(request);

        return roomRepository.save(room);
    }


    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    public Room getRoom(String id) {
        return roomRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public Room updateRoom(String id, RoomRequest request) {

        Room room = roomMaper.toRoom(request);
        return roomRepository.save(room);
    }


    public void deleteRoom(String id) {
        roomRepository.deleteById(id);
    }
}
