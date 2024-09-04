package com.example.hotelmaster.repository;

import com.example.hotelmaster.constant.RoomStatus;
import com.example.hotelmaster.constant.RoomType;
import com.example.hotelmaster.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
//    @Query("SELECT distinct r.roomType FROM Room r")
//    List<RoomType> findRoomTypes();
//
//    @Query("SELECT room_number FROM room r "+
//    "where r.room_type LIKE %:roomType% "+
//    "and r.room_number NOT IN ("+
//    "select br.room_number FROM booking br "+
//    "WHERE ((br.check_in_date <= :checkOutDate ) And (br.check_out_date >= :checkInDate))")
//    List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

//    @Query("SELECT r FROM Room r " +
//            "WHERE r.id NOT IN (" +
//            "  SELECT b.roomNumber FROM Booking b " +
//            "  WHERE :today BETWEEN b.checkInDate AND b.checkOutDate" +
//            ")")
//    List<Room> findAvailableRoomsToday(@Param("today") LocalDate today);

    @Query("SELECT r FROM Room r " +
            "WHERE r.id NOT IN (" +
            "  SELECT b.roomNumber FROM Booking b " +
            "  WHERE (b.checkInDate <= :endDate AND b.checkOutDate >= :startDate)" +
            ")")
    List<Room> findAvailableRoomsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
