package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.PaymentRequest;
import com.example.hotelmaster.dto.request.ServicesRequest;
import com.example.hotelmaster.entity.Payment;
import com.example.hotelmaster.entity.Services;
import com.example.hotelmaster.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentController {

    PaymentService paymentService;


    @PostMapping
    Payment createPayment(@RequestBody PaymentRequest request) {
        return paymentService.createPayment(request);
    }

    @GetMapping
    List<Payment> getAllPayment() {
        return paymentService.getAllPayment();
    }

    @GetMapping("/{Id}")
    Payment getPayment(@PathVariable("Id") Long Id) {
        return paymentService.getPayment(Id);
    }

    @PutMapping("/{Id}")
    Payment updatePayment(@PathVariable Long Id, @RequestBody PaymentRequest request) {
        return paymentService.updatePayment(Id, request);
    }

    @DeleteMapping("/{Id}")
    String deletePayment(@PathVariable Long Id) {
        paymentService.deletePayment(Id);
        return "Payment deleted";
    }

    /**
     * API để tạo một Payment và tính tổng tiền của các Service liên kết.
     *
     * @param bookingId ID của Booking liên kết.
     * @param payment Thông tin Payment cần tạo.
     * @param serviceIds Danh sách ID của Service cần liên kết.
     * @return Payment đã tạo với tổng chi phí.
     */
    @PostMapping("/create-with-services")
    public ResponseEntity<Payment> createPaymentWithServices(
            @RequestParam Long bookingId,
            @RequestBody Payment payment,
            @RequestParam List<Long> serviceIds) {
        Payment createdPayment = paymentService.createPaymentWithTotalCost(bookingId, payment, serviceIds);
        return ResponseEntity.ok(createdPayment);
    }
}