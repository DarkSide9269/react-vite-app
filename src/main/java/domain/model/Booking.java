package domain.model;

import java.util.UUID;

public record Booking(String id, String roomId, String guestName, DateRange dates) {
    public static Booking create(String roomId, String guestName, DateRange dates) {
        return new Booking(UUID.randomUUID().toString(), roomId, guestName, dates);
    }
}