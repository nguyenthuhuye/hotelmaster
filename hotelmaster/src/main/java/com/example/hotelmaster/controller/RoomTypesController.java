package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.dto.request.RoomTypesRequest;
import com.example.hotelmaster.entity.Room;
import com.example.hotelmaster.entity.RoomTypes;
import com.example.hotelmaster.service.RoomTypesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomtypes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoomTypesController {
    RoomTypesService roomTypesService;

    @PostMapping
    RoomTypes createRoomTypes(@RequestBody RoomTypesRequest request) {
        return roomTypesService.createRoomtypes(request);
    }

    @GetMapping
    List<RoomTypes> getAllRoomTypes() {
        return roomTypesService.getAllRoomTypes();
    }

    @GetMapping("/{Id}")
    RoomTypes getRoomTypes(@PathVariable("Id") String Id) {
        return roomTypesService.getRoomTypes(Id);
    }

    @PutMapping("/{Id}")
    RoomTypes updateRoomTypes(@PathVariable String Id, @RequestBody RoomTypesRequest request) {
        return roomTypesService.updateRoomTypes(Id, request);
    }

    @DeleteMapping("/{Id}")
    String deleteRoom(@PathVariable String Id) {
        roomTypesService.deleteRoomTypes(Id);
        return "RoomTypes deleted";
    }
}
