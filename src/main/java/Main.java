import application.service.BookingService;
import infrastructure.adapter.in.ConsoleController;
import infrastructure.adapter.out.InMemoryBookingRepository;
import infrastructure.adapter.out.InMemoryRoomRepository;

public class Main {
    public static void main(String[] args) {
        // 1. Ініціалізація secondary adapters (БД)
        InMemoryRoomRepository roomRepository = new InMemoryRoomRepository();
        InMemoryBookingRepository bookingRepository = new InMemoryBookingRepository();

        // 2. Ініціалізація ядра (Application Service)
        BookingService bookingService = new BookingService(roomRepository, bookingRepository);

        // 3. Ініціалізація primary adapter (UI)
        ConsoleController controller = new ConsoleController(bookingService);

        // Тестування сценаріїв
        System.out.println("--- Сценарій 1: Успішне бронювання ---");
        controller.handleBookingRequest("101", "Олександр", "2026-07-01", "2026-07-05");

        System.out.println("\n--- Сценарій 2: Конфлікт дат (перетин) ---");
        controller.handleBookingRequest("101", "Марія", "2026-07-03", "2026-07-07");

        System.out.println("\n--- Сценарій 3: Кімната виведена з експлуатації ---");
        controller.handleBookingRequest("102", "Іван", "2026-08-01", "2026-08-05");

        System.out.println("\n--- Сценарій 4: Некоректні дати ---");
        controller.handleBookingRequest("101", "Анна", "2026-07-10", "2026-07-08");
    }
}