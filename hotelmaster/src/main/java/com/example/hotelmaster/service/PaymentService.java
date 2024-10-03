package com.example.hotelmaster.service;

import com.example.hotelmaster.constant.BookingStatus;
import com.example.hotelmaster.constant.RoomStatus;
import com.example.hotelmaster.dto.request.PaymentRequest;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.dto.request.UserUpdateRequest;
import com.example.hotelmaster.dto.response.PaymentResponse;
import com.example.hotelmaster.dto.response.RoomResponse;
import com.example.hotelmaster.entity.*;
import com.example.hotelmaster.repository.BookingRepository;
import com.example.hotelmaster.repository.PaymentRepository;
import com.example.hotelmaster.repository.ServicesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentService {
    PaymentRepository paymentRepository;
    BookingRepository bookingRepository;
    private final ServicesRepository servicesRepository;

    @Transactional
    public PaymentResponse createPayment(Long bookingId, PaymentRequest request) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));

        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod(request.getPaymentMethod());

        Payment savedPayment = paymentRepository.save(payment);

        // Cập nhật trạng thái của booking
        booking.setBookingStatus(BookingStatus.COMPLETED);
        booking.setPayment(savedPayment);
        bookingRepository.save(booking);

        PaymentResponse response = new PaymentResponse();
        response.setBookingId(booking.getId());
        response.setAmount(payment.getAmount());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setPaymentDate(payment.getPaymentDate());

        return response;
    }

//    public List<Payment> getAllPayment() {
//        return paymentRepository.findAll();
//    }


    public List<PaymentResponse> getAllPayment() {
        List<Payment> payments = paymentRepository.findAll();  // Lấy toàn bộ danh sách
        return payments.stream()
                .sorted(Comparator.comparing(Payment::getPaymentDate).reversed())
                .map(this::createPaymentResponse)
                .collect(Collectors.toList());
    }
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("payment not found with id: " + id));

        return createPaymentResponse(payment);
    }

    @Transactional
    public PaymentResponse updatePayment(Long id, PaymentRequest request) {
        Payment existingpayment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("payment not found with id: " + id));

        existingpayment.setAmount(request.getAmount());
        existingpayment.setPaymentDate(LocalDate.now());
        existingpayment.setPaymentMethod(request.getPaymentMethod());
        Payment updatedPayment = paymentRepository.save(existingpayment);
        return createPaymentResponse(updatedPayment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    private PaymentResponse createPaymentResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        if (payment.getBooking() != null) {
            response.setBookingId(payment.getBooking().getId());
        } else {
            response.setBookingId(null);
//            log.warn("Booking with ID {} has no associated user", booking.getId());
        }
        response.setId(payment.getId());
        response.setPaymentDate(payment.getPaymentDate());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setAmount(payment.getAmount());
        return response;
    }

}