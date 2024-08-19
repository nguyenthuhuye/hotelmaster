package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.dto.request.UserCreationRequest;
import com.example.hotelmaster.dto.request.UserUpdateRequest;
import com.example.hotelmaster.entity.Booking;
import com.example.hotelmaster.entity.User;
import com.example.hotelmaster.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingController {

    BookingService bookingService;

    @PostMapping
    Booking createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }


    @GetMapping
    List<Booking> getAllBooking() {
        return bookingService.getAllBooking();
    }

    @GetMapping("/{Id}")
    Booking getBooking(@PathVariable("Id") String Id) {
        return bookingService.getBooking(Id);
    }

    @PutMapping("/{Id}")
    Booking updateBooking(@PathVariable String Id, @RequestBody BookingRequest request) {
        return bookingService.updateBooking(Id, request);
    }

    @DeleteMapping("/{Id}")
    String deleteUser(@PathVariable String Id) {
        bookingService.deleteBooking(Id);
        return "Booking deleted";
    }
}
