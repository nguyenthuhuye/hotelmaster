package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.entity.Booking;
import com.example.hotelmaster.entity.Room;
import com.example.hotelmaster.service.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoomController {

    RoomService roomServicel;

    @PostMapping
    Room createRoom(@RequestBody RoomRequest request) {
        return roomServicel.createRoom(request);
    }


    @GetMapping
    List<Room> getAllRoom() {
        return roomServicel.getAllRoom();
    }

    @GetMapping("/{Id}")
    Room getRoom(@PathVariable("Id") String Id) {
        return roomServicel.getRoom(Id);
    }

    @PutMapping("/{Id}")
    Room updateRoom(@PathVariable String Id, @RequestBody RoomRequest request) {
        return roomServicel.updateRoom(Id, request);
    }

    @DeleteMapping("/{Id}")
    String deleteRoom(@PathVariable String Id) {
        roomServicel.deleteRoom(Id);
        return "Room deleted";
    }
}
