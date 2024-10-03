package com.example.hotelmaster.dto;

import com.example.hotelmaster.entity.Booking;
import com.example.hotelmaster.entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class UserSerializer  extends StdSerializer<User> {
    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", user.getId());
        gen.writeStringField("username", user.getUsername());
        gen.writeStringField("email", user.getEmail());
        // Serialize other fields as needed

        gen.writeArrayFieldStart("bookings");
        for (Booking booking : user.getBookings()) {
            gen.writeStartObject();
            gen.writeNumberField("id", booking.getId());
            gen.writeStringField("roomNumber", booking.getRoomNumber());
            // Add other necessary booking fields without including the full user object
            gen.writeEndObject();
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}
