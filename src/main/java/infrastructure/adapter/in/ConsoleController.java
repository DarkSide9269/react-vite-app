package infrastructure.adapter.in;

import application.port.in.BookRoomCommand;
import application.port.in.BookRoomUseCase;
import domain.model.Booking;

import java.time.LocalDate;

public class ConsoleController {
    private final BookRoomUseCase bookRoomUseCase;

    public ConsoleController(BookRoomUseCase bookRoomUseCase) {
        this.bookRoomUseCase = bookRoomUseCase;
    }

    public void handleBookingRequest(String roomNumber, String guestName, String start, String end) {
        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);

            BookRoomCommand command = new BookRoomCommand(roomNumber, guestName, startDate, endDate);
            Booking booking = bookRoomUseCase.bookRoom(command);

            System.out.println("✅ Успіх! Бронювання створено. ID: " + booking.id());
        } catch (Exception e) {
            System.out.println("❌ Помилка бронювання: " + e.getMessage());
        }
    }
}