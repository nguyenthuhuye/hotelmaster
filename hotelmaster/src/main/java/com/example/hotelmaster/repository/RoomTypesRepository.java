package com.example.hotelmaster.repository;

import com.example.hotelmaster.entity.RoomTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypesRepository extends JpaRepository<RoomTypes, String> {
}
