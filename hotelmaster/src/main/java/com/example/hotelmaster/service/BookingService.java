package com.example.hotelmaster.service;

import com.example.hotelmaster.constant.BookingStatus;
import com.example.hotelmaster.dto.request.BookingCreateRequest;
import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.entity.*;
import com.example.hotelmaster.mapper.BookingMapper;
import com.example.hotelmaster.repository.BookingRepository;
import com.example.hotelmaster.repository.RoomRepository;
import com.example.hotelmaster.repository.ServicesRepository;
import com.example.hotelmaster.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
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

    public Booking createBooking(BookingRequest request) {
//        Booking booking = new Booking();
//        booking.setUser(request.getUserId());
//        booking.setRoomNumber(request.getRoomNumber());
//        booking.setUserName(request.getUserName());
//        booking.setCheckInDate(request.getCheckInDate());
//        booking.setCheckOutDate(request.getCheckOutDate());
//        booking.setTotalPrice(request.getTotalPrice());
//        booking.setBookingStatus(BookingStatus.BOOKED);
//        booking.setEmail(request.getEmail());
//        booking.setAdress(request.getAdress());
//        booking.setPhoneNumer(request.getPhoneNumer());
////        booking.setServices(request.getServiceId());
//        List<Services> services = new ArrayList<>();
//
//        // Kiểm tra và gán giá trị cho services
//        if (request.getServiceId() != null && !request.getServiceId().isEmpty()) {
//            for (Long serviceId : request.getServiceId()) {
//                Services service = servicesRepository.findById(serviceId)
//                        .orElseThrow(() -> new RuntimeException("Service not found with id: " + serviceId));
//                services.add(service);
//            }
//            booking.setServices(services);
//            System.out.println("Services set successfully: " + services);
//        } else {
//            // Nếu request không có service IDs, set danh sách trống
//            booking.setServices(Collections.emptyList());
//            System.out.println("No services provided, setting empty list.");
//        }
//
//        return bookingRepository.save(booking);

        //        List<User> users = new ArrayList<>();
////         Kiểm tra và gán giá trị cho
//        if (request.getUserId() != null && !request.getUserId().isEmpty()) {
//            for (Long userId : request.getUserId()) {
//                User user = userRepository.findById(userId)
//                        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
//                users.add(user);
//            }
//            booking.setUser(users);
//            System.out.println("User set successfully: " + users);
//        } else {
//            // Nếu request không có service IDs, set danh sách trống
//            booking.setUser(Collections.emptyList());
//            System.out.println("No user provided, setting empty list.");
//        }
//        if (request.getUserId() == null || request.getUserId().isEmpty()) {
//            throw new IllegalArgumentException("User IDs list cannot be null or empty");
//        }
//        Room room = roomRepository.findByRoomNumber(request.getRoomNumber())
//                .orElseThrow(() -> new RuntimeException("Room not found with number: " + request.getRoomNumber()));

//        List<User> users = request.getUserId().stream()
//                .map(userId -> userRepository.findById(userId)
//                        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId)))
//                .collect(Collectors.toList());
        Booking booking = new Booking();
        booking.setUsers(request.getUserId());
        booking.setRoomNumber(request.getRoomNumber());
        booking.setUserName(request.getUserName());
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setTotalPrice(request.getTotalPrice());
        booking.setBookingStatus(BookingStatus.BOOKED);
        booking.setEmail(request.getEmail());
        booking.setAdress(request.getAdress());
        booking.setPhoneNumer(request.getPhoneNumer());

        List<Services> services = new ArrayList<>();

        // Ưu tiên sử dụng serviceId nếu có
        if (request.getServiceId() != null && !request.getServiceId().isEmpty()) {
            for (Long serviceId : request.getServiceId()) {
                Services service = servicesRepository.findById(serviceId)
                        .orElseThrow(() -> new RuntimeException("Service not found with id: " + serviceId));
                services.add(service);
            }
        }
        // Nếu không có serviceId, sử dụng serviceUpdate
        else if (request.getServiceUpdate() != null && !request.getServiceUpdate().isEmpty()) {
            String jsonServiceIds = convertServiceUpdate(request.getServiceUpdate());
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

        return bookingRepository.save(booking);
    }


    public Booking createBookingWithServices(Long bookingId, List<Long> serviceIds) {
        // Tạo hoặc lấy Booking
        Booking booking = bookingRepository.findById(bookingId).orElse(new Booking());
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    public Booking getBooking(Long id) {
        return bookingRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Transactional
    public Booking updateBooking(Long id, BookingRequest request) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        boolean priceChanged = false;
        BigDecimal oldTotalPrice = booking.getTotalPrice();

//        if (request.getUserId() != null) {
//            booking.setUser(userRepository.findById(request.getUserId())
//                    .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId())).getId());
//        }
        if (request.getRoomNumber() != null) {
            booking.setRoomNumber(request.getRoomNumber());
        }
        if (request.getUserName() != null) {
            booking.setUserName(request.getUserName());
        }
        if (request.getCheckInDate() != null) {
            booking.setCheckInDate(request.getCheckInDate());
        }
        if (request.getCheckOutDate() != null) {
            booking.setCheckOutDate(request.getCheckOutDate());
        }
        if (request.getEmail() != null) {
            booking.setEmail(request.getEmail());
        }
        if (request.getAdress() != null) {
            booking.setAdress(request.getAdress());
        }
        if (request.getPhoneNumer() != null) {
            booking.setPhoneNumer(request.getPhoneNumer());
        }

        // Xử lý cập nhật dịch vụ và tính toán lại giá
        List<Long> serviceIds = new ArrayList<>();
        if (request.getServiceId() != null && !request.getServiceId().isEmpty()) {
            serviceIds = request.getServiceId();
        } else if (request.getServiceUpdate() != null && !request.getServiceUpdate().isEmpty()) {
            String jsonServiceIds = convertServiceUpdate(request.getServiceUpdate());
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
            BigDecimal oldServicesPrice = booking.getServices().stream()
                    .map(Services::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal priceDifference = additionalPrice.subtract(oldServicesPrice);

            booking.setServices(newServices);

            // Cập nhật totalPrice
            if (priceDifference.compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal newTotalPrice = oldTotalPrice.add(priceDifference);
                booking.setTotalPrice(newTotalPrice);
                priceChanged = true;
            }

            System.out.println("Services updated successfully: " + newServices);
        }

        Booking updatedBooking = bookingRepository.save(booking);

        if (priceChanged) {
            System.out.println("Total price updated from " + oldTotalPrice + " to " + updatedBooking.getTotalPrice());
        }

        return updatedBooking;
    }
//    public Booking updateBooking(Long id, BookingRequest request) {
//
//        Booking booking = bookingRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
//
//        boolean priceChanged = false;
//        BigDecimal oldTotalPrice = booking.getTotalPrice();
//
//        if (request.getUserId() != null) {
//            booking.setUser(userRepository.findById(request.getUserId())
//                    .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId())).getId());
//        }
//        if (request.getRoomNumber() != null) {
//            booking.setRoomNumber(request.getRoomNumber());
//        }
//        if (request.getUserName() != null) {
//            booking.setUserName(request.getUserName());
//        }
//        if (request.getCheckInDate() != null) {
//            booking.setCheckInDate(request.getCheckInDate());
//        }
//        if (request.getCheckOutDate() != null) {
//            booking.setCheckOutDate(request.getCheckOutDate());
//        }
//        if (request.getEmail() != null) {
//            booking.setEmail(request.getEmail());
//        }
//        if (request.getAdress() != null) {
//            booking.setAdress(request.getAdress());
//        }
//        if (request.getPhoneNumer() != null) {
//            booking.setPhoneNumer(request.getPhoneNumer());
//        }
//
//        // Xử lý cập nhật dịch vụ và tính toán lại giá
//        if (request.getServiceId() != null) {
//            List<Services> newServices = new ArrayList<>();
//            BigDecimal additionalPrice = BigDecimal.ZERO;
//
//            for (Long serviceId : request.getServiceId()) {
//                Services service = servicesRepository.findById(serviceId)
//                        .orElseThrow(() -> new RuntimeException("Service not found with id: " + serviceId));
//                newServices.add(service);
//                additionalPrice = additionalPrice.add(service.getPrice());
//            }
//
//            // Tính toán sự chênh lệch giá
//            BigDecimal oldServicesPrice = booking.getServices().stream()
//                    .map(Services::getPrice)
//                    .reduce(BigDecimal.ZERO, BigDecimal::add);
//            BigDecimal priceDifference = additionalPrice.subtract(oldServicesPrice);
//
//            booking.setServices(newServices);
//
//            // Cập nhật totalPrice
//            if (priceDifference.compareTo(BigDecimal.ZERO) != 0) {
//                BigDecimal newTotalPrice = oldTotalPrice.add(priceDifference);
//                booking.setTotalPrice(newTotalPrice);
//                priceChanged = true;
//            }
//
//            System.out.println("Services updated successfully: " + newServices);
//        }
//
//        // Nếu request chỉ định totalPrice mới, sử dụng giá trị đó thay vì tính toán
////        if (request.getTotalPrice() != null) {
////            booking.setTotalPrice(new BigDecimal(String.valueOf(request.getTotalPrice())));
////            priceChanged = true;
////        }
//
//        Booking updatedBooking = bookingRepository.save(booking);
//
//        if (priceChanged) {
//            System.out.println("Total price updated from " + oldTotalPrice + " to " + updatedBooking.getTotalPrice());
//        }
//
//        return updatedBooking;
//    }


    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }


    public List<Booking> getBookingByUserId(Long userId){
        return bookingRepository.findBookingByUserId(userId);
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
