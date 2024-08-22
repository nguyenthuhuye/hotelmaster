package com.example.hotelmaster.controller;

import com.example.hotelmaster.constant.RoomStatus;
import com.example.hotelmaster.constant.RoomType;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.entity.Room;
import com.example.hotelmaster.service.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
//@CrossOrigin(origins = "*")
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
    Room getRoom(@PathVariable("Id") String Id) {
        return roomService.getRoom(Id);
    }

    @PutMapping("/{Id}")
    Room updateRoom(@PathVariable String Id, @RequestBody RoomRequest request) {
        return roomService.updateRoom(Id, request);
    }

    @DeleteMapping("/{Id}")
    String deleteRoom(@PathVariable String Id) {
        roomService.deleteRoom(Id);
        return "Room deleted";
    }

//    @GetMapping("/{AvailableRooms}")
//    List<Room> getAvailableRooms(@PathVariable String roomTypes, LocalDate checkInDate, LocalDate checkOutDate) {
//        return roomServicel.findAvailableRooms(roomTypes, checkInDate, checkOutDate);
//    }

//    @GetMapping("/available")
//    public List<Room> getAvailableRooms(
//            @RequestParam String roomType,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {
//        return roomService.findAvailableRooms(roomType, checkIn, checkOut);
//    }

//    @GetMapping("/available")
//    Optional<Room> getAvailableRoom(@RequestBody RoomType roomType, RoomStatus roomStatus) {
//        return roomService.findRoom(roomType, roomStatus);
//    }

    @GetMapping("/available-in-range")
    public ResponseEntity<List<Room>> getAvailableRoomsInDateRange(@RequestParam("startDate") String startDate,
                                                                   @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Room> availableRooms = roomService.getAvailableRoomsInDateRange(start, end);
        return ResponseEntity.ok(availableRooms);
    }
}
