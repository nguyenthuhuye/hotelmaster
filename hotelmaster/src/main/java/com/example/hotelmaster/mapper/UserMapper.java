package com.example.hotelmaster.mapper;

import com.example.hotelmaster.dto.request.UserCreationRequest;
import com.example.hotelmaster.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

//    UserResponse toUserResponse(User user);
//
////    @Mapping(target = "roles", ignore = true)
//    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}