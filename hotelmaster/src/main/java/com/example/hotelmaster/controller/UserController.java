package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.UserCreationRequest;
import com.example.hotelmaster.dto.request.UserUpdateRequest;
import com.example.hotelmaster.dto.response.UserResponse;
import com.example.hotelmaster.entity.User;
import com.example.hotelmaster.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping
    User createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/admin")
    User createAdmin(@RequestBody UserCreationRequest request) {
        return userService.createAdmin(request);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        UserResponse response = userService.getUserById(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping  // Phương thức GET
    public List<UserResponse> getAllUser() {
        return userService.getAllUser();  // Gọi hàm lấy danh sách phòng
    }

    @PutMapping("/admin/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        UserResponse updateUser = userService.updateUser(userId, userUpdateRequest);
        return ResponseEntity.ok(updateUser);
    }

        @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }



}