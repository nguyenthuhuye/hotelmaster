package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.AvailableRoomProjection;
import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.dto.response.BookingResponse;
import com.example.hotelmaster.dto.response.GetRoomResponse;
import com.example.hotelmaster.dto.response.RoomResponse;
import com.example.hotelmaster.entity.Room;
import com.example.hotelmaster.entity.RoomTypes;
import com.example.hotelmaster.repository.RoomRepository;
import com.example.hotelmaster.repository.RoomTypesRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoomController {
    RoomService roomService;
    RoomRepository roomRepository;
    RoomTypesRepository roomTypesRepository;

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomRequest roomRequest) {
        RoomResponse response = roomService.createRoom(roomRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable Long id) {
        RoomResponse response = roomService.getRoomById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping  // Phương thức GET
    public List<RoomResponse> getAllRooms() {
        return roomService.getAllRoom();  // Gọi hàm lấy danh sách phòng
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long id, @RequestBody RoomRequest roomRequest) {
        RoomResponse updateRoom = roomService.updateRoom(id, roomRequest);
        return ResponseEntity.ok(updateRoom);
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

    @GetMapping("/user/{id}")
    public ResponseEntity<GetRoomResponse> getRoomById(@PathVariable Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id)
                ;

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            RoomTypes roomType = roomTypesRepository.findByRoomType(room.getRoomType())
                    .orElseThrow(() -> new RuntimeException("RoomType not found"));

            GetRoomResponse response = new GetRoomResponse(
                    room.getId(),
                    room.getRoomNumber(),
                    room.getImageUrl(),
                    room.getRoomStatus(),
                    room.getRoomType(),
                    roomType.getPrice()
            );

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

