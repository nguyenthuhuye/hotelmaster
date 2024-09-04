package com.example.hotelmaster.service;

import com.example.hotelmaster.constant.Role;
import com.example.hotelmaster.dto.request.UserCreationRequest;
import com.example.hotelmaster.dto.request.UserUpdateRequest;
import com.example.hotelmaster.entity.User;
import com.example.hotelmaster.exception.AppException;
import com.example.hotelmaster.exception.ErrorCode;
import com.example.hotelmaster.mapper.UserMapper;
import com.example.hotelmaster.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
//    RoleRepository roleRepository;
    UserMapper userMapper;
//    PasswordEncoder passwordEncoder;


    public User createUser(UserCreationRequest request) {
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.ROLE_USER);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public User createAdmin(UserCreationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.ROLE_ADMIN);

//        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);

    }

    public User updateAdmin(String userId, UserUpdateRequest request) {
        User user = getUser(userId);

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.ROLE_ADMIN);
        return userRepository.save(user);

    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
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

