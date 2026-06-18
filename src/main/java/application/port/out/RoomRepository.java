package application.port.out;

import domain.model.Room;
import java.util.Optional;

public interface RoomRepository {
    Optional<Room> findByNumber(String roomNumber);
}