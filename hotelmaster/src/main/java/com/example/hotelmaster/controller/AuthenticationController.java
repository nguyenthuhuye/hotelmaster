package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.ApiResponse;
import com.example.hotelmaster.dto.request.AuthenticationRequest;
import com.example.hotelmaster.dto.request.UserCreationRequest;
import com.example.hotelmaster.dto.response.AuthenticationResponse;
import com.example.hotelmaster.dto.response.UserCreationResponse;
import com.example.hotelmaster.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .authenticated(response.isAuthenticated())
                        .userId(response.getUserId())
                        .role(response.getRole())
                        .userName(request.getUserName())
                        .build())
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<UserCreationResponse> createUser(@RequestBody UserCreationRequest request) {
        UserCreationResponse response = authenticationService.saveUser(request);
        return ApiResponse.<UserCreationResponse>builder()
                .result(UserCreationResponse.builder()
                        .authenticated(response.isAuthenticated())
                        .userId(response.getUserId())
                        .role(response.getRole())
                        .userName(request.getUsername())
                        .build())
                .build();
    }


}
