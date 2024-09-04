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
import org.springframework.web.bind.annotation.*;

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
    Payment getPayment(@PathVariable("Id") String Id) {
        return paymentService.getPayment(Id);
    }

    @PutMapping("/{Id}")
    Payment updatePayment(@PathVariable String Id, @RequestBody PaymentRequest request) {
        return paymentService.updatePayment(Id, request);
    }

    @DeleteMapping("/{Id}")
    String deletePayment(@PathVariable String Id) {
        paymentService.deletePayment(Id);
        return "Payment deleted";
    }
}
