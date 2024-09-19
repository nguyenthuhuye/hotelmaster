package com.example.hotelmaster.repository;

import com.example.hotelmaster.entity.Booking;
import com.example.hotelmaster.entity.Services;
import com.example.hotelmaster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.checkInDate BETWEEN :startDate AND :endDate")
    List<Booking> findBookingsBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Câu query tùy chỉnh để lấy danh sách Service theo Booking ID (nếu cần)
    @Query("SELECT b.services FROM Booking b WHERE b.id = :bookingId")
    List<Services> findServicesByBookingId(@Param("bookingId") Long bookingId);


    List<Booking> findBookingByUserId (Long userId);

    List<Booking> findBookingByUserName (String userName);

    List<Booking> findByUserIdAndRoomNumberIn(Long userId, List<String> roomNumbers);
}
