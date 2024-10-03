package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.PaymentRequest;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.dto.request.ServicesRequest;
import com.example.hotelmaster.dto.response.PaymentResponse;
import com.example.hotelmaster.dto.response.RoomResponse;
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


//    @PostMapping
//    Payment createPayment(@RequestBody PaymentRequest request) {
//        return paymentService.createPayment(request);
//    }
    @GetMapping  // Phương thức GET
    public List<PaymentResponse> getAllPayment() {
        return paymentService.getAllPayment();  // Gọi hàm lấy danh sách phòng
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id) {
        PaymentResponse response = paymentService.getPaymentById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Long id, @RequestBody PaymentRequest paymentRequest) {
        PaymentResponse updatePayment = paymentService.updatePayment(id, paymentRequest);
        return ResponseEntity.ok(updatePayment);
    }


    @DeleteMapping("/{Id}")
    String deletePayment(@PathVariable Long Id) {
        paymentService.deletePayment(Id);
        return "Payment deleted";
    }

}