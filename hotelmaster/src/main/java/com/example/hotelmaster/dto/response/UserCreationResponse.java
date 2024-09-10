package com.example.hotelmaster.dto.response;

import com.example.hotelmaster.constant.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationResponse {
    boolean authenticated;
    String userId;
    Role role;
    String username;
}
