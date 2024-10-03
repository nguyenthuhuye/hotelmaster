package com.example.hotelmaster.service;

import com.example.hotelmaster.constant.RoomStatus;
import com.example.hotelmaster.constant.RoomType;
import com.example.hotelmaster.dto.AvailableRoomProjection;
import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.dto.response.BookingResponse;
import com.example.hotelmaster.dto.response.RoomResponse;
import com.example.hotelmaster.entity.Booking;
import com.example.hotelmaster.entity.Room;
import com.example.hotelmaster.entity.Services;
import com.example.hotelmaster.entity.User;
import com.example.hotelmaster.mapper.RoomMaper;
import com.example.hotelmaster.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoomService {
    RoomRepository roomRepository;
    RoomMaper roomMaper;

    @Transactional
    public RoomResponse createRoom(RoomRequest roomRequest) {

        // Create and save the booking
        Room room= new Room();
        room.setRoomStatus(RoomStatus.AVAILABLE);
        room.setRoomType(roomRequest.getRoomType());
        room.setImageUrl(roomRequest.getImageUrl());
        room.setRoomNumber(roomRequest.getRoomNumber());
        Room savedRoom = roomRepository.save(room);

        // Create and return the response
        return createRoomResponse(savedRoom);
    }

    public RoomResponse getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));

        return createRoomResponse(room);
    }

    public List<RoomResponse> getAllRoom() {
        List<Room> rooms = roomRepository.findAll();  // Lấy toàn bộ danh sách phòng
        return rooms.stream()
                .map(this::createRoomResponse)
                .collect(Collectors.toList());
    }
    @Transactional
    public RoomResponse updateRoom(Long id, RoomRequest request) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));

        existingRoom.setRoomNumber(request.getRoomNumber());
        existingRoom.setRoomType(request.getRoomType());
        existingRoom.setRoomStatus(request.getRoomStatus());
        existingRoom.setImageUrl(request.getImageUrl());
        Room updatedRoom = roomRepository.save(existingRoom);
        return createRoomResponse(updatedRoom);
    }


    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }


    public List<AvailableRoomProjection> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        return roomRepository.findAvailableRooms(checkInDate, checkOutDate);
    }

    private RoomResponse createRoomResponse(Room room) {
        RoomResponse response = new RoomResponse();
        response.setRoomNumber(room.getRoomNumber());
        response.setImageUrl(room.getImageUrl());
        response.setRoomStatus(room.getRoomStatus());
        response.setRoomType(room.getRoomType());
        response.setId(room.getId());
        return response;
    }


}
