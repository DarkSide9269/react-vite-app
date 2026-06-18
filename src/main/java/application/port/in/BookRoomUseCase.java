package application.port.in;

import domain.model.Booking;

public interface BookRoomUseCase {
    Booking bookRoom(BookRoomCommand command);
}