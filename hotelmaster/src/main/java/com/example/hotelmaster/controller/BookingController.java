package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.dto.request.PaymentRequest;
import com.example.hotelmaster.dto.response.BookingResponse;
import com.example.hotelmaster.dto.response.PaymentResponse;
import com.example.hotelmaster.dto.response.RoomResponse;
import com.example.hotelmaster.entity.Booking;
import com.example.hotelmaster.entity.Payment;
import com.example.hotelmaster.repository.BookingRepository;
import com.example.hotelmaster.service.BookingService;
import com.example.hotelmaster.service.ExcelExportService;
import com.example.hotelmaster.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

    BookingService bookingService;

    ExcelExportService excelExportService;

    BookingRepository bookingRepository;

    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest) {
        BookingResponse response = bookingService.createBooking(bookingRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable Long id) {
        BookingResponse response = bookingService.getBookingById(id);
        return ResponseEntity.ok(response);
    }

//    @GetMapping
//    public ResponseEntity<List<BookingResponse>> getAllBookings(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        List<BookingResponse> bookings = bookingService.getAllBookings(page, size);
//        return ResponseEntity.ok(bookings);
//    }
    @GetMapping  // Phương thức GET
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();  // Gọi hàm lấy danh sách phòng
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable Long id, @RequestBody BookingRequest bookingRequest) {
        BookingResponse updatedBooking = bookingService.updateBooking(id, bookingRequest);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{Id}")
    String deleteUser(@PathVariable Long Id) {
        bookingService.deleteBooking(Id);
        return "Booking deleted";
    }


    @GetMapping("/export")
    public ResponseEntity<byte[]> exportBookingsToExcel(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws IOException {

        List<Booking> bookings = bookingRepository.findBookingsBetweenDates(startDate, endDate);
        ByteArrayInputStream in = excelExportService.exportBookingsToExcel(bookings);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=bookings.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(in.readAllBytes());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<BookingResponse>> getBookingsByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<BookingResponse> bookings = bookingService.getBookingsByUserId(userId, page, size);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/{bookingId}/payments")
    public ResponseEntity<PaymentResponse> createPayment(@PathVariable Long bookingId, @RequestBody PaymentRequest request) {
        PaymentResponse payment = paymentService.createPayment(bookingId, request);
        return ResponseEntity.ok(payment);
    }

}
