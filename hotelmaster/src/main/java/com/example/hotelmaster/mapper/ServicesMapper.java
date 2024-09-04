package com.example.hotelmaster.mapper;

import com.example.hotelmaster.dto.request.ServicesRequest;
import com.example.hotelmaster.entity.Services;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicesMapper {
    Services toService (ServicesRequest request);
}