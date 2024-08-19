package com.example.hotelmaster.dto.request;


import com.example.hotelmaster.constant.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String firstName;
    String lastName;
    String email;
    String phone;
    String password;
    String username;
    Role role;
}
