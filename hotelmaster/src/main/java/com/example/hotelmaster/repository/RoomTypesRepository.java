package com.example.hotelmaster.repository;

import com.example.hotelmaster.constant.RoomType;
import com.example.hotelmaster.entity.RoomTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomTypesRepository extends JpaRepository<RoomTypes, Long> {
    Optional<RoomTypes> findByRoomType(RoomType roomType);
}
