package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.AvailableRoomProjection;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.entity.Room;
import com.example.hotelmaster.service.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoomController {


    RoomService roomService;

    @PostMapping
    Room createRoom(@RequestBody RoomRequest request) {
        return roomService.createRoom(request);
    }


    @GetMapping
    List<Room> getAllRoom() {
        return roomService.getAllRoom();
    }

    @GetMapping("/{Id}")
    Room getRoom(@PathVariable("Id") Long Id) {
        return roomService.getRoom(Id);
    }
    @GetMapping("getroomNumber/{roomNumber}")
    Room getRoom1(@PathVariable("roomNumber") String roomNumber) {
        return roomService.getRoom1(roomNumber);
    }
    @PutMapping("/{Id}")
    Room updateRoom(@PathVariable Long Id, @RequestBody RoomRequest request) {
        return roomService.updateRoom(Id, request);
    }

    @DeleteMapping("/{Id}")
    String deleteRoom(@PathVariable Long Id) {
        roomService.deleteRoom(Id);
        return "Room deleted";
    }

    @GetMapping("/available")
    public ResponseEntity<List<AvailableRoomProjection>> getAvailableRooms(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {
        List<AvailableRoomProjection> availableRooms = roomService.getAvailableRooms(checkInDate, checkOutDate);
        return ResponseEntity.ok(availableRooms);
    }

    @GetMapping("/available-in-range")
    public ResponseEntity<List<Room>> getAvailableRoomsInDateRange(@RequestParam("startDate") String startDate,
                                                                   @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Room> availableRooms = roomService.getAvailableRoomsInDateRange(start, end);
        return ResponseEntity.ok(availableRooms);
    }
}
