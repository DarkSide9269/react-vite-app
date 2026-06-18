package infrastructure.adapter.out;

import application.port.out.RoomRepository;
import domain.model.Room;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRoomRepository implements RoomRepository {
    private final Map<String, Room> rooms = new HashMap<>();

    public InMemoryRoomRepository() {
        // Симуляція існуючих даних
        rooms.put("101", new Room("r-1", "101", true));
        rooms.put("102", new Room("r-2", "102", false)); // Недоступний для бронювання
    }

    @Override
    public Optional<Room> findByNumber(String roomNumber) {
        return rooms.values().stream()
                .filter(r -> r.number().equals(roomNumber))
                .findFirst();
    }
}