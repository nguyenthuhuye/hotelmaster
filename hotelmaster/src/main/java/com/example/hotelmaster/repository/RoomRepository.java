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

//    @Query("SELECT distinct r.roomType FROM Room r")
//    List<RoomType> findRoomTypes();
//
//    @Query("SELECT room_number FROM room r "+
//    "where r.room_type LIKE %:roomType% "+
//    "and r.room_number NOT IN ("+
//    "select br.room_number FROM booking br "+
//    "WHERE ((br.check_in_date <= :checkOutDate ) And (br.check_out_date >= :checkInDate))")
//    List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

    @Query("SELECT r FROM Room r " +
            "WHERE r.id NOT IN (" +
            "  SELECT b.roomNumber FROM Booking b " +
            "  WHERE (b.checkInDate <= :endDate AND b.checkOutDate >= :startDate)" +
            ")")
    List<Room> findAvailableRoomsInDateRange(@Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);
}
