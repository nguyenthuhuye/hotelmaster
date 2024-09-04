package com.example.hotelmaster.service;

import com.example.hotelmaster.dto.request.PaymentRequest;
import com.example.hotelmaster.dto.request.UserUpdateRequest;
import com.example.hotelmaster.entity.Booking;
import com.example.hotelmaster.entity.Payment;
import com.example.hotelmaster.entity.Services;
import com.example.hotelmaster.entity.User;
import com.example.hotelmaster.repository.BookingRepository;
import com.example.hotelmaster.repository.PaymentRepository;
import com.example.hotelmaster.repository.ServicesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentService {
    PaymentRepository paymentRepository;
    BookingRepository bookingRepository;
    private final ServicesRepository servicesRepository;


    public Payment createPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod(request.getPaymentMethod());

        return paymentRepository.save(payment);
    }
    /**
     * Tạo một Payment mới và tính tổng chi phí của các Service liên kết.
     *
     * @param bookingId ID của Booking liên kết.
     * @param payment Payment thông tin cần tạo.
     * @param serviceIds Danh sách ID của Service cần liên kết.
     * @return Payment đã tạo với tổng chi phí.
     */
    public Payment createPaymentWithTotalCost(Long bookingId, Payment payment, List<Long> serviceIds) {
        // Lấy Booking từ cơ sở dữ liệu dựa trên bookingId
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));

        // Lấy các Service từ cơ sở dữ liệu dựa trên danh sách serviceIds
        List<Services> services = servicesRepository.findAllById(serviceIds);

        // Tính tổng tiền từ danh sách Service
        BigDecimal totalCost = services.stream()
                .map(Services::getPrice)  // Lấy giá của từng Service
                .reduce(BigDecimal.ZERO, BigDecimal::add);  // Tính tổng giá

        // Cập nhật tổng chi phí vào Payment
        payment.setAmount(totalCost);
        payment.setBooking(booking);

        // Lưu Payment vào cơ sở dữ liệu
        return paymentRepository.save(payment);
    }


    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment updatePayment(Long id, PaymentRequest request) {
        Payment payment = getPayment(id);
        payment.setAmount(request.getAmount());
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod(request.getPaymentMethod());

        return paymentRepository.save(payment);
    }


    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

}