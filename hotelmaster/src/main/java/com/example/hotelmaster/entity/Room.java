package com.example.hotelmaster.entity;

import com.example.hotelmaster.constant.RoomStatus;
import com.example.hotelmaster.constant.RoomType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String roomNumber;
    String imageUrl;
    RoomStatus roomStatus;
    RoomType roomType;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)

    Set<Booking> bookings;

}
