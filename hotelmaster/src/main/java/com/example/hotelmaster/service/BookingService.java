package com.example.hotelmaster.service;

import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.entity.Booking;
import com.example.hotelmaster.entity.Room;
import com.example.hotelmaster.mapper.BookingMapper;
import com.example.hotelmaster.repository.BookingRepository;
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
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;

    public Booking createBooking(BookingRequest request) {

        Booking booking = bookingMapper.toBooking(request);

        return bookingRepository.save(booking);
    }


    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    public Booking getBooking(String id) {
        return bookingRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public Booking updateBooking(String id, BookingRequest request) {

        Booking booking = bookingMapper.toBooking(request);
        return bookingRepository.save(booking);
    }


    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}
