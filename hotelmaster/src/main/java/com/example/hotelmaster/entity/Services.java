package com.example.hotelmaster.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "service")
@Data
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String serviceName;
    String description;
    BigDecimal price;
}
