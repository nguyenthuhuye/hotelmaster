package com.example.hotelmaster.repository;

import com.example.hotelmaster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
//    boolean existsByUsername(String username);
//
//    Optional<User> findByUsername(String username);
}
