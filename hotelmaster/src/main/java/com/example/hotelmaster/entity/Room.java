package com.example.hotelmaster.entity;

import com.example.hotelmaster.constant.RoomStatus;
import com.example.hotelmaster.constant.RoomType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String roomNumber;
    String imageUrl;
    RoomStatus roomStatus;
//    String roomStatus;
    RoomType roomType;
//    String roomType;
//    String roomType;
    @OneToMany(mappedBy = "roomNumber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Booking> bookings = new HashSet<>();

}
