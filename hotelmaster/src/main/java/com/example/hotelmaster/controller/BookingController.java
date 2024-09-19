package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.dto.request.PaymentRequest;
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
    Booking createBooking(@RequestBody BookingRequest request) {
    return bookingService.createBooking(request);
    }

    @GetMapping
    List<Booking> getAllBooking() {
        List<Booking> bookings = new ArrayList<>();
        try {
            bookings = bookingService.getAllBooking();
        } catch (Exception e) {
            LOGGER.error("{}", e);
        }
        return bookings;
    }
    @GetMapping("/{userName}")
    Booking getBooking(@PathVariable("userName") Long Id) {
        return bookingService.getBooking(Id);
    }

    @PutMapping("/{Id}")
    Booking updateBooking(@PathVariable Long Id, @RequestBody BookingRequest request) {
        return bookingService.updateBooking(Id, request);
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

    @PostMapping("/{bookingId}/add-services")
    public ResponseEntity<Booking> addServicesToBooking(
            @PathVariable Long bookingId,
            @RequestBody List<Long> serviceIds) {
        Booking booking = bookingService.createBookingWithServices(bookingId, serviceIds);
        return ResponseEntity.ok(booking);
    }

//    @GetMapping("/getbooking/{userName}")
//    List<Booking> getBookingByUserName(@PathVariable("userName") String userName){
//    return bookingService.getBookingByUserName(userName);
//    }
    @GetMapping("/getbooking/{userId}")
    List<Booking> getBookingByUserId(@PathVariable("userId") Long userId) {
    return bookingService.getBookingByUserId(userId);
    }

    @PostMapping("/{bookingId}/payments")
    public ResponseEntity<Payment> createPayment(@PathVariable Long bookingId, @RequestBody PaymentRequest request) {
        Payment payment = paymentService.createPayment(bookingId, request);
        return ResponseEntity.ok(payment);
    }

}
