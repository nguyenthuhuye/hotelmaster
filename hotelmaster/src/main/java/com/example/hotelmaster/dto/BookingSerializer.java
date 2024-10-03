package com.example.hotelmaster.dto;

import com.example.hotelmaster.entity.Booking;
import com.example.hotelmaster.entity.Services;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class BookingSerializer extends StdSerializer<Booking> {
    public BookingSerializer() {
        this(null);
    }

    public BookingSerializer(Class<Booking> t) {
        super(t);
    }

    @Override
    public void serialize(Booking booking, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", booking.getId());
        gen.writeStringField("userName", booking.getUserName());
        gen.writeStringField("roomNumber", booking.getRoomNumber());
        gen.writeStringField("checkInDate", booking.getCheckInDate().toString());
        gen.writeStringField("checkOutDate", booking.getCheckOutDate().toString());
        gen.writeNumberField("totalPrice", booking.getTotalPrice());
        gen.writeStringField("bookingStatus", booking.getBookingStatus().toString());
        gen.writeStringField("email", booking.getEmail());
        gen.writeStringField("adress", booking.getAdress());
        gen.writeStringField("phoneNumer", booking.getPhoneNumer());

        if (booking.getRoom() != null) {
            gen.writeObjectFieldStart("room");
            gen.writeNumberField("id", booking.getRoom().getId());
            gen.writeStringField("roomNumber", booking.getRoom().getRoomNumber());
            // Add other necessary room fields
            gen.writeEndObject();
        }

        if (booking.getUser() != null) {
            gen.writeObjectFieldStart("user");
            gen.writeNumberField("id", booking.getUser().getId());
            // Add other necessary user fields
            gen.writeEndObject();
        }

        if (booking.getPayment() != null) {
            gen.writeObjectFieldStart("payment");
            gen.writeNumberField("id", booking.getPayment().getId());
            // Add other necessary payment fields
            gen.writeEndObject();
        }

        gen.writeArrayFieldStart("services");
        for (Services service : booking.getServices()) {
            gen.writeStartObject();
            gen.writeNumberField("id", service.getId());
            // Add other necessary service fields
            gen.writeEndObject();
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}
