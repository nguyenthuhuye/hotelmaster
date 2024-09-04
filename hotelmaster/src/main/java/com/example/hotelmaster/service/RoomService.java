package com.example.hotelmaster.service;

import com.example.hotelmaster.constant.RoomStatus;
import com.example.hotelmaster.constant.RoomType;
import com.example.hotelmaster.dto.AvailableRoomDTO;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.entity.Room;
import com.example.hotelmaster.mapper.RoomMaper;
import com.example.hotelmaster.repository.RoomRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Room getRoom(Long id) {
        return roomRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public Room updateRoom(Long id, RoomRequest request) {

        Room room = getRoom(id);
        room.setRoomNumber(request.getRoomNumber());
        room.setRoomType(request.getRoomType());
        room.setRoomStatus(request.getRoomStatus());
        room.setImageUrl(request.getImageUrl());
        return roomRepository.save(room);
    }


    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

//    public List<AvailableRoomDTO> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
//        List<Object[]> results = roomRepository.findAvailableRoomsWithPriceBetweenDates(startDate, endDate);
//        List<AvailableRoomDTO> availableRooms = new ArrayList<>();
//
//        for (Object[] result : results) {
//            Room room = (Room) result[0];
//            BigDecimal price = (BigDecimal) result[1];
//            availableRooms.add(new AvailableRoomDTO(room.getId(), room.getRoomNumber(), price));
//        }
//
//        return availableRooms;
//    }

    public List<Room> getAvailableRoomsInDateRange(LocalDate startDate, LocalDate endDate) {
        return roomRepository.findAvailableRoomsInDateRange(startDate, endDate);
    }
}
