package com.example.hotelmaster.service;

import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.dto.response.BookingResponse;
import com.example.hotelmaster.dto.response.RoomResponse;
import com.example.hotelmaster.entity.*;
import com.example.hotelmaster.repository.BookingRepository;
import com.example.hotelmaster.repository.RoomRepository;
import com.example.hotelmaster.repository.ServicesRepository;
import com.example.hotelmaster.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingService {
    BookingRepository bookingRepository;
    ServicesRepository servicesRepository;
    UserRepository userRepository;
    RoomRepository roomRepository;
    @Transactional
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        // Validate and fetch related entities
        Room room = roomRepository.findById(bookingRequest.getRoomId())
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        User user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//        List<Services> services = servicesRepository.findAllById(bookingRequest.getServiceId());

        // Create and save the booking
        Booking booking = new Booking();
        booking.setRoomNumber(bookingRequest.getRoomNumber());
        booking.setRoom(room);
        booking.setUser(user);
        booking.setUserName(bookingRequest.getUserName());
        booking.setCheckInDate(bookingRequest.getCheckInDate());
        booking.setCheckOutDate(bookingRequest.getCheckOutDate());
        booking.setTotalPrice(bookingRequest.getTotalPrice());
        booking.setBookingStatus(bookingRequest.getBookingStatus());
        booking.setPhoneNumer(bookingRequest.getPhoneNumer());
        booking.setEmail(bookingRequest.getEmail());
        booking.setAdress(bookingRequest.getAdress());
//        booking.setServices(services);

        List<Services> services = new ArrayList<>();
        // Ưu tiên sử dụng serviceId nếu có
        if (bookingRequest.getServiceId() != null && !bookingRequest.getServiceId().isEmpty()) {
            for (Long serviceId : bookingRequest.getServiceId()) {
                Services service = servicesRepository.findById(serviceId)
                        .orElseThrow(() -> new RuntimeException("Service not found with id: " + serviceId));
                services.add(service);
            }
        }
        // Nếu không có serviceId, sử dụng serviceUpdate
        else if (bookingRequest.getServiceUpdate() != null && !bookingRequest.getServiceUpdate().isEmpty()) {
            String jsonServiceIds = convertServiceUpdate(bookingRequest.getServiceUpdate());
            JSONObject jsonObject = new JSONObject(jsonServiceIds);
            JSONArray serviceIdArray = jsonObject.getJSONArray("serviceId");

            for (int i = 0; i < serviceIdArray.length(); i++) {
                Long serviceId = serviceIdArray.getLong(i);
                Services service = servicesRepository.findById(serviceId)
                        .orElseThrow(() -> new RuntimeException("Service not found with id: " + serviceId));
                services.add(service);
            }
        }
        if (!services.isEmpty()) {
            booking.setServices(services);
            System.out.println("Services set successfully: " + services);
        } else {
            booking.setServices(Collections.emptyList());
            System.out.println("No services provided, setting empty list.");
        }

        Booking savedBooking = bookingRepository.save(booking);

        // Create and return the response
        return createBookingResponse(savedBooking);
    }

    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));
        return createBookingResponse(booking);
    }

//    public List<BookingResponse> getAllBookings(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Booking> bookingPage = bookingRepository.findAll(pageable);
//        return bookingPage.getContent().stream()
//                .map(this::createBookingResponse)
//                .collect(Collectors.toList());
//    }

    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();  // Lấy toàn bộ danh sách phòng
        return bookings.stream()
                .sorted(Comparator.comparing(Booking::getCheckInDate).reversed())
                .map(this::createBookingResponse)
                .collect(Collectors.toList());
    }

    private BookingResponse createBookingResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setRoomNumber(booking.getRoomNumber());
        // Null check for Room
        if (booking.getRoom() != null) {
            response.setRoomId(booking.getRoom().getId());
        } else {
            response.setRoomId(null);
            // Optionally log this unexpected state
            log.warn("Booking with ID {} has no associated room", booking.getId());
        }

        // Null check for User
        if (booking.getUser() != null) {
            response.setUserId(booking.getUser().getId());
        } else {
            response.setUserId(null);
            log.warn("Booking with ID {} has no associated user", booking.getId());
        }
        response.setUserName(booking.getUserName());
        response.setCheckInDate(booking.getCheckInDate());
        response.setCheckOutDate(booking.getCheckOutDate());
        response.setTotalPrice(booking.getTotalPrice());
        response.setBookingStatus(booking.getBookingStatus());
        response.setPhoneNumber(booking.getPhoneNumer());
        response.setId(booking.getId());
        response.setEmail(booking.getEmail());
        response.setAdress(booking.getAdress());

        // Null check for Services
        if (booking.getServices() != null) {
            response.setServiceId(booking.getServices().stream()
                    .map(Services::getId)
                    .collect(Collectors.toList()));
        } else {
            response.setServiceId(Collections.emptyList());
        }
        return response;
    }


    @Transactional
    public BookingResponse updateBooking(Long id, BookingRequest bookingRequest) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));

        // Update room if provided in the request
        if (bookingRequest.getRoomId() != null) {
            Room newRoom = roomRepository.findById(bookingRequest.getRoomId())
                    .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + bookingRequest.getRoomId()));
            existingBooking.setRoom(newRoom);
        } else if (existingBooking.getRoom() == null) {
            // If no room is provided and the existing booking doesn't have a room, log a warning
            log.warn("Booking with ID {} has no associated room and no new room was provided", id);
        }

        // Update user if provided in the request
        if (bookingRequest.getUserId() != null) {
            User newUser = userRepository.findById(bookingRequest.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + bookingRequest.getUserId()));
            existingBooking.setUser(newUser);
        }

        if (bookingRequest.getRoomNumber() != null) {
            existingBooking.setRoomNumber(bookingRequest.getRoomNumber());
        }
        if (bookingRequest.getUserName() != null) {
            existingBooking.setUserName(bookingRequest.getUserName());
        }
        if (bookingRequest.getCheckInDate() != null) {
            existingBooking.setCheckInDate(bookingRequest.getCheckInDate());
        }
        if (bookingRequest.getCheckOutDate() != null) {
            existingBooking.setCheckOutDate(bookingRequest.getCheckOutDate());
        }
        if (bookingRequest.getEmail() != null) {
            existingBooking.setEmail(bookingRequest.getEmail());
        }
        if (bookingRequest.getAdress() != null) {
            existingBooking.setAdress(bookingRequest.getAdress());
        }
        if (bookingRequest.getPhoneNumer() != null) {
            existingBooking.setPhoneNumer(bookingRequest.getPhoneNumer());
        }
        if (bookingRequest.getUserName() != null) {
            existingBooking.setUserName(bookingRequest.getUserName());
        }

        boolean priceChanged = false;
        BigDecimal oldTotalPrice = existingBooking.getTotalPrice();
        // Xử lý cập nhật dịch vụ và tính toán lại giá
        List<Long> serviceIds = new ArrayList<>();
        if (bookingRequest.getServiceId() != null && !bookingRequest.getServiceId().isEmpty()) {
            serviceIds = bookingRequest.getServiceId();
        } else if (bookingRequest.getServiceUpdate() != null && !bookingRequest.getServiceUpdate().isEmpty()) {
            String jsonServiceIds = convertServiceUpdate(bookingRequest.getServiceUpdate());
            JSONObject jsonObject = new JSONObject(jsonServiceIds);
            JSONArray serviceIdArray = jsonObject.getJSONArray("serviceId");
            for (int i = 0; i < serviceIdArray.length(); i++) {
                serviceIds.add(serviceIdArray.getLong(i));
            }
        }

        if (!serviceIds.isEmpty()) {
            List<Services> newServices = new ArrayList<>();
            BigDecimal additionalPrice = BigDecimal.ZERO;

            for (Long serviceId : serviceIds) {
                Services service = servicesRepository.findById(serviceId)
                        .orElseThrow(() -> new RuntimeException("Service not found with id: " + serviceId));
                newServices.add(service);
                additionalPrice = additionalPrice.add(service.getPrice());
            }

            // Tính toán sự chênh lệch giá
            BigDecimal oldServicesPrice = existingBooking.getServices().stream()
                    .map(Services::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal priceDifference = additionalPrice.subtract(oldServicesPrice);

            existingBooking.setServices(newServices);

            // Cập nhật totalPrice
            if (priceDifference.compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal newTotalPrice = oldTotalPrice.add(priceDifference);
                existingBooking.setTotalPrice(newTotalPrice);
                priceChanged = true;
            }

            System.out.println("Services updated successfully: " + newServices);
        }

        Booking updatedBooking = bookingRepository.save(existingBooking);

        if (priceChanged) {
            System.out.println("Total price updated from " + oldTotalPrice + " to " + updatedBooking.getTotalPrice());
        }

        return createBookingResponse(updatedBooking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public Page<BookingResponse> getBookingsByUserId(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Booking> bookings = bookingRepository.findByUserId(userId, pageable);
        return bookings.map(this::createBookingResponse);
    }

    public String convertServiceUpdate(String serviceUpdate) {
        String[] idStrings = serviceUpdate.split(",");
        JSONArray serviceIds = new JSONArray();
        for (String idString : idStrings) {
            serviceIds.put(Long.parseLong(idString.trim()));
        }
        JSONObject result = new JSONObject();
        result.put("serviceId", serviceIds);
        return result.toString();
    }
}
