package com.example.hotelmaster.mapper;

import com.example.hotelmaster.dto.request.BookingRequest;
import com.example.hotelmaster.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toBooking(BookingRequest request);
}
