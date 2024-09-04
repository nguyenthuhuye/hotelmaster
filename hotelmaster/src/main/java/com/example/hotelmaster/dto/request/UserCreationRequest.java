package com.example.hotelmaster.dto.request;


import com.example.hotelmaster.constant.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    String fullName;
//    @Email(message = "")
    String email;
    String phone;
    String password;
//    @Size(min = 6, message = "USER_INVALID")
    String username;
    Role role;
}
