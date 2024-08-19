package com.example.hotelmaster.entity;

import com.example.hotelmaster.constant.RoomStatus;
import com.example.hotelmaster.constant.RoomType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String roomNumber;
    String imageUrl;
    RoomStatus roomStatus;
    RoomType roomType;
}
