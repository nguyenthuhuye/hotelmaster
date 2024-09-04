package com.example.hotelmaster.service;

import com.example.hotelmaster.dto.request.PaymentRequest;
import com.example.hotelmaster.dto.request.UserUpdateRequest;
import com.example.hotelmaster.entity.Payment;
import com.example.hotelmaster.entity.User;
import com.example.hotelmaster.repository.BookingRepository;
import com.example.hotelmaster.repository.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentService {
    PaymentRepository paymentRepository;
    BookingRepository bookingRepository;

    public Payment createPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setBookingId(request.getBookingId());
        payment.setAmount(request.getAmount());
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod(request.getPaymentMethod());

        return paymentRepository.save(payment);
    }


    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    public Payment getPayment(String id) {
        return paymentRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment updatePayment(String id, PaymentRequest request) {
        Payment payment = getPayment(id);
        payment.setBookingId(request.getBookingId());
        payment.setAmount(request.getAmount());
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod(request.getPaymentMethod());

        return paymentRepository.save(payment);
    }


    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }

}
