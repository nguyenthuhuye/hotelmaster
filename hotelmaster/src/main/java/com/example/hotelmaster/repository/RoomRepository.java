package com.example.hotelmaster.repository;

import com.example.hotelmaster.dto.AvailableRoomProjection;
import com.example.hotelmaster.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findRoomByroomNumber (String roomNumber);
    Room findByRoomNumber(String roomNuber);

//    @Query("SELECT r FROM Room r " +
//            "WHERE r.id NOT IN (" +
//            "  SELECT b.roomNumber FROM Booking b " +
//            "  WHERE (b.checkInDate <= :endDate AND b.checkOutDate >= :startDate)" +
//            ")")
//    List<Room> findAvailableRoomsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT DISTINCT r.room_number AS roomNumber, rt.room_type AS roomType, rt.price, r.image_url AS imageUrl, r.id AS id " +
            "FROM room r " +
            "JOIN room_types rt ON r.room_type = rt.room_type " +
            "WHERE r.room_number NOT IN ( " +
            "    SELECT b.room_number " +
            "    FROM booking b " +
            "    WHERE (b.check_in_date <= :checkOutDate AND b.check_out_date >= :checkInDate) " +
            ") " +
            "ORDER BY r.room_number", nativeQuery = true)
    List<AvailableRoomProjection> findAvailableRooms(@Param("checkInDate") LocalDate checkInDate,
                                                     @Param("checkOutDate") LocalDate checkOutDate);

}
