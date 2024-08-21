package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.entity.Booking;
import com.example.hotelmaster.repository.BookingRepository;
import com.example.hotelmaster.service.BookingService;
import com.example.hotelmaster.service.ExcelExportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingController {

    BookingService bookingService;

    ExcelExportService excelExportService;

    BookingRepository bookingRepository;

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

//    @GetMapping("/export")
//    public ResponseEntity<byte[]> exportBookingsToExcel() throws IOException {
//        List<Booking> bookings = bookingRepository.findAll();
//        ByteArrayInputStream in = excelExportService.exportBookingsToExcel(bookings);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=bookings.xlsx");
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(in.readAllBytes());
//    }

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

}
