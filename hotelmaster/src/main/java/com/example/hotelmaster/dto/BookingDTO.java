package com.example.hotelmaster.dto;

import com.example.hotelmaster.constant.BookingStatus;
import com.example.hotelmaster.entity.Booking;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingDTO {
    private Long id;
    private String userName;
    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BigDecimal totalPrice;
    private BookingStatus bookingStatus;
    private String email;
    private String address;
    private String phoneNumber;
    private Long roomId;
    private Long userId;

    // Constructors, getters, and setters

    public static BookingDTO fromBooking(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setUserName(booking.getUserName());
        dto.setRoomNumber(booking.getRoomNumber());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setTotalPrice(booking.getTotalPrice());
        dto.setBookingStatus(booking.getBookingStatus());
        dto.setEmail(booking.getEmail());
        dto.setAddress(booking.getAdress());  // Note: There's a typo in your original entity (adress)
        dto.setPhoneNumber(booking.getPhoneNumer());  // Note: There's a typo in your original entity (phoneNumer)
        dto.setRoomId(booking.getRoom() != null ? booking.getRoom().getId() : null);
        dto.setUserId(booking.getUser() != null ? booking.getUser().getId() : null);
        return dto;
    }
}
