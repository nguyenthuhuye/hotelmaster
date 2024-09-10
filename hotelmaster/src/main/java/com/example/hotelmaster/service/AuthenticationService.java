package com.example.hotelmaster.service;

import com.example.hotelmaster.constant.Role;
import com.example.hotelmaster.dto.request.AuthenticationRequest;
import com.example.hotelmaster.dto.request.UserCreationRequest;
import com.example.hotelmaster.dto.response.AuthenticationResponse;
import com.example.hotelmaster.dto.response.UserCreationResponse;
import com.example.hotelmaster.entity.User;
import com.example.hotelmaster.exception.AppException;
import com.example.hotelmaster.exception.ErrorCode;
import com.example.hotelmaster.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.hotelmaster.constant.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Retrieve the user from the database using the username from the request
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Get the user ID
        String userId = String.valueOf(user.getId());
        Role role = Role.valueOf(String.valueOf(user.getRole()));
        String username = user.getUsername();
        // Create a password encoder with a strength of 10
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        // Verify the password from the request matches the stored password
        boolean isAuthenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        // Return an AuthenticationResponse with the authentication status and user ID
        return new AuthenticationResponse(isAuthenticated, userId, role,username);
    }

    public UserCreationResponse saveUser(UserCreationRequest request) {
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setRole(ROLE_USER);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);

        UserCreationResponse response = new UserCreationResponse();
        response.setAuthenticated(true);
        response.setUserId(user.getId().toString());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        return response;
    }
}
