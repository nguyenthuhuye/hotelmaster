package com.example.hotelmaster.repository;

import com.example.hotelmaster.entity.Payment;
import com.example.hotelmaster.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
}
