package application.port.out;

import domain.model.Booking;
import java.util.List;

public interface BookingRepository {
    void save(Booking booking);
    List<Booking> findBookingsForRoom(String roomId);
}