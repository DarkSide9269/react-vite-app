package infrastructure.adapter.out;

import application.port.out.BookingRepository;
import domain.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBookingRepository implements BookingRepository {
    private final List<Booking> bookings = new ArrayList<>();

    @Override
    public void save(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public List<Booking> findBookingsForRoom(String roomId) {
        return bookings.stream()
                .filter(b -> b.roomId().equals(roomId))
                .toList();
    }
}