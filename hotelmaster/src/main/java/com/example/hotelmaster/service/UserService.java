package com.example.hotelmaster.service;

import com.example.hotelmaster.constant.Role;
import com.example.hotelmaster.constant.RoomStatus;
import com.example.hotelmaster.dto.request.RoomRequest;
import com.example.hotelmaster.dto.request.UserCreationRequest;
import com.example.hotelmaster.dto.request.UserUpdateRequest;
import com.example.hotelmaster.dto.response.RoomResponse;
import com.example.hotelmaster.dto.response.UserResponse;
import com.example.hotelmaster.entity.Room;
import com.example.hotelmaster.entity.User;
import com.example.hotelmaster.exception.AppException;
import com.example.hotelmaster.exception.ErrorCode;
import com.example.hotelmaster.mapper.UserMapper;
import com.example.hotelmaster.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User createUser(UserCreationRequest request) {
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setRole(Role.ROLE_USER);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public User createAdmin(UserCreationRequest request) {
        User user = new User();
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        user.setUsername(request.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setRole(Role.ROLE_ADMIN);
        User savedUser = userRepository.save(user);

        // Create and return the response
        return userRepository.save(user);
    }



    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        return createUserResponse(user);
    }

    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();  // Lấy toàn bộ danh sách phòng
        return users.stream()
                .map(this::createUserResponse)
                .collect(Collectors.toList());
    }
    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        existingUser.setUsername(request.getUsername());
        existingUser.setPassword(request.getPassword());
        existingUser.setEmail(request.getEmail());
        existingUser.setPhone(request.getPhone());
        existingUser.setFullName(request.getFullName());
//        existingUser.setRole(request.getRole());
        if (request.getRole() != null) {
            existingUser.setRole(request.getRole());
        }
        User updatedUser = userRepository.save(existingUser);
        return createUserResponse(updatedUser);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.getBookings().clear(); // Xóa liên kết với bookings
            userRepository.delete(user);
        });
    }
    private UserResponse createUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setId(user.getId());
        return response;
    }



























//    public UserResponse createUser(UserCreationRequest request) {
//
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        HashSet<Role> roles = new HashSet<>();
//        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);
//
//        user.setRoles(roles);
//
//        try {
//            user = userRepository.save(user);
//        } catch (DataIntegrityViolationException exception) {
//            throw new AppException(ErrorCode.USER_EXISTED);
//        }

//        if(userRepository.existsByUsername(request.getUsername()))
//            throw new AppException(ErrorCode.USER_EXISTED);
//        User user = userMapper.toUser(request);
//
//        return userRepository.save(user);
//    }
//
//    public User updateUser(String userId, UserUpdateRequest request) {
//        Optional<User> user = userRepository.findById(userId);
//        userMapper.updateUser(user, request);
//        return userRepository.save(user);
//    }

//    public UserResponse getMyInfo() {
//        var context = SecurityContextHolder.getContext();
//        String name = context.getAuthentication().getName();
//
//        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//
//        return userMapper.toUserResponse(user);
//    }

//    @PostAuthorize("returnObject.username == authentication.name")
//    public UserResponse updateUser(String userId, UserUpdateRequest request) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//
//        userMapper.updateUser(user, request);
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        var roles = roleRepository.findAllById(request.getRoles());
//        user.setRoles(new HashSet<>(roles));
//
//        return userMapper.toUserResponse(userRepository.save(user));
//    }

//    @PreAuthorize("hasRole('ADMIN')")
//    public void deleteUser(String userId) {
//        userRepository.deleteById(userId);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    public List<UserResponse> getUsers() {
//        log.info("In method get Users");
//        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    public UserResponse getUser(String id) {
//        return userMapper.toUserResponse(
//                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
//    }
}

