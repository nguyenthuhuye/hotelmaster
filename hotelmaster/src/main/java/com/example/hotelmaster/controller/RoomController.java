package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.AvailableRoomDTO;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.entity.Room;
import com.example.hotelmaster.service.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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

    @PutMapping("/{Id}")
    Room updateRoom(@PathVariable Long Id, @RequestBody RoomRequest request) {
        return roomService.updateRoom(Id, request);
    }

    @DeleteMapping("/{Id}")
    String deleteRoom(@PathVariable Long Id) {
        roomService.deleteRoom(Id);
        return "Room deleted";
    }

//    @GetMapping("/available")
//    public List<AvailableRoomDTO> getAvailableRooms(
//            @RequestParam("startDate") LocalDate startDate,
//            @RequestParam("endDate") LocalDate endDate) {
//        return roomService.findAvailableRooms(startDate, endDate);
//    }
}
