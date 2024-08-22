package com.example.hotelmaster.entity;

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
@Table(name = "roomTypes")
@Data
public class RoomTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    RoomType roomType;
    String description;
    Integer capactity;
    //Sức chứa
    Integer pricePerNight;
    //Giá mỗi đêm

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Room> rooms = new HashSet<>();

}
