package com.example.hotelmaster.service;

import com.example.hotelmaster.constant.BookingStatus;
import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.entity.*;
import com.example.hotelmaster.mapper.BookingMapper;
import com.example.hotelmaster.repository.BookingRepository;
import com.example.hotelmaster.repository.ServicesRepository;
import com.example.hotelmaster.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    UserRepository repository;
    ServicesRepository servicesRepository;

//    public Booking createBooking(BookingRequest request) {
//
//        Booking booking = bookingMapper.toBooking(request);
//        return bookingRepository.save(booking);
//    }

    public Booking createBooking(BookingRequest request) {
        Booking booking = new Booking();
        booking.setRoomNumber(request.getRoomNumber());
        booking.setUserName(request.getUserName());
        booking.setBookingStatus(BookingStatus.BOOKED);
        booking.setTotalPrice(request.getTotalPrice());
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        return bookingRepository.save(booking);
    }

    public Booking createBookingWithServices(Long bookingId, List<Long> serviceIds) {
        // Tạo hoặc lấy Booking
        Booking booking = bookingRepository.findById(bookingId).orElse(new Booking());

        // Lấy danh sách Service từ serviceIds
        List<Services> services = servicesRepository.findAllById(serviceIds);

        // Thêm các dịch vụ vào Booking
        booking.getServices().addAll(services);

        // Lưu Booking, JPA sẽ tự động lưu vào bảng liên kết
        return bookingRepository.save(booking);
    }



    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    public Booking getBooking(Long id) {
        return bookingRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public Booking updateBooking(Long id, BookingRequest request) {

        Booking booking = getBooking(id);
        booking.setRoomNumber(request.getRoomNumber());
        booking.setUserName(request.getUserName());
        booking.setBookingStatus(request.getBookingStatus());
        booking.setTotalPrice(request.getTotalPrice());
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        return bookingRepository.save(booking);
    }


    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getBookingByUserId(Long userId){
        return bookingRepository.findBookingByUserId(userId);
//                orElseThrow(()-> new RuntimeException("Don't have booking"));
    }
}
