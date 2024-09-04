package com.example.hotelmaster.dto.response;

import com.example.hotelmaster.constant.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String fullName;
    String email;
    String phone;
    String password;
    String username;
    Role role;
}
