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
public interface RoomRepository extends JpaRepository<Room, String> {
//    @Query("SELECT DISTINCT r.roomType FROM Room r")
//    List<String> findRoomTypes();
//
//    @Query("SELECT r FROM Room r " +
//            "WHERE r.roomTypes LIKE %:roomTypes% " +
//            "AND r.id NOT IN (" +
//            "    SELECT br.room.id FROM booking br " +
//            "    WHERE br.checkInDate <= :checkOut AND br.checkOutDate >= :checkIn" +
//            ")")
//    List<Room> findAvailableRooms(
//            @Param("checkIn") LocalDate checkInDate,
//            @Param("checkOut") LocalDate checkOutDate,
//            @Param("roomType") String roomType
//    );
//    @Query("SELECT DISTINCT r.roomType FROM Room r")
//    List<String> findRoomTypes();
//
//
//    @Query("SELECT r FROM Room r WHERE r.roomType = :roomType AND " +
//            "NOT EXISTS (SELECT b FROM Booking b WHERE b.roomNumber = r AND " +
//            "(:checkInDate < b.checkOutDate AND :checkOutDate > b.checkInDate))")
//    List<Room> findAvailableRoomsByTypeAndDate(
//            @Param("roomTypes") String roomTypes,
//            @Param("checkInDate") LocalDate checkInDate,
//            @Param("checkOutDate") LocalDate checkOutDate);

//    @Query("SELECT r FROM Room r WHERE r.roomType = :roomType " +
//            "AND NOT EXISTS (SELECT b FROM Booking b WHERE b.roomNumber = r " +
//            "AND (:checkInDate < b.checkOutDate AND :checkOutDate > b.checkInDate))")
//    List<Room> findAvailableRoomsByTypeAndDate(
//            @Param("roomType") String roomType,
//            @Param("checkInDate") LocalDate checkInDate,
//            @Param("checkOutDate") LocalDate checkOutDate
//    );
//
//    Optional<Room> findByRoomTypeAnAndRoomStatus(RoomType roomType, RoomStatus roomStatus);
}
