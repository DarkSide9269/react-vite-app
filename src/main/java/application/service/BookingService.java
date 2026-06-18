package application.service;

import application.port.in.BookRoomCommand;
import application.port.in.BookRoomUseCase;
import application.port.out.BookingRepository;
import application.port.out.RoomRepository;
import domain.model.Booking;
import domain.model.DateRange;
import domain.model.Room;

import java.util.List;

public class BookingService implements BookRoomUseCase {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public BookingService(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking bookRoom(BookRoomCommand command) {
        Room room = roomRepository.findByNumber(command.roomNumber())
                .orElseThrow(() -> new IllegalArgumentException("Номер не знайдено."));

        if (!room.isAvailable()) {
            throw new IllegalStateException("Номер наразі виведений з експлуатації.");
        }

        DateRange requestedDates = new DateRange(command.startDate(), command.endDate());
        List<Booking> existingBookings = bookingRepository.findBookingsForRoom(room.id());

        // Бізнес-правило: Перевірка перетину дат
        boolean isOccupied = existingBookings.stream()
                .anyMatch(b -> b.dates().overlaps(requestedDates));

        if (isOccupied) {
            throw new IllegalStateException("Номер вже заброньовано на обрані дати.");
        }

        Booking newBooking = Booking.create(room.id(), command.guestName(), requestedDates);
        bookingRepository.save(newBooking);

        return newBooking;
    }
}