package com.example.hotelmaster.repository;

import com.example.hotelmaster.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
//    @Query("SELECT r, rt.price FROM Room r " +
//            "JOIN r.roomType rt " +
//            "WHERE r.id NOT IN (" +
//            "  SELECT b.room.id FROM Booking b " +
//            "  WHERE b.checkInDate < :endDate  AND b.checkOutDate > :startDate" +
//            ")")
//    List<Object[]> findAvailableRoomsWithPriceBetweenDates(
//            @Param("startDate") LocalDate startDate,
//            @Param("endDate") LocalDate endDate);

//    @Query("SELECT DISTINCT r, rt.price FROM room r " +
//            "JOIN r.roomType rt " +
//            "WHERE r.id NOT IN (" +
//            "    SELECT b.room.id FROM booking b " +
//            "    WHERE (b.checkInDate <= :endDate AND b.checkOutDate >= :startDate)" +
//            ")")
//    List<Object[]> findAvailableRoomsWithPriceBetweenDates(
//            @Param("startDate") LocalDate startDate,
//            @Param("endDate") LocalDate endDate
//    );
}
