package application.port.in;

import java.time.LocalDate;

public record BookRoomCommand(String roomNumber, String guestName, LocalDate startDate, LocalDate endDate) {}