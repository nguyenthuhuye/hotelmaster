package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.ApiResponse;
import com.example.hotelmaster.dto.request.UserCreationRequest;
import com.example.hotelmaster.dto.request.UserUpdateRequest;
import com.example.hotelmaster.entity.User;
import com.example.hotelmaster.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    UserService userService;

    @PostMapping
    User createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }
//    User createUser(@RequestBody @Valid UserCreationRequest request) {
//        return userService.createUser(request);
//    }

//    @PostMapping
//    ApiResponse<User> createUser (@RequestBody UserCreationRequest request){
//        ApiResponse<User> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(userService.createUser(request));
//        return apiResponse;
//    }

    @PostMapping("/admin")
    User createAdmin(@RequestBody UserCreationRequest request) {
        return userService.createAdmin(request);
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.getUser();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @PutMapping("/admin/{userId}")
    User updateAdmin(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateAdmin(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }


}