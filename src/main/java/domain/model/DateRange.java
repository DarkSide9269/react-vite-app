package domain.model;

import java.time.LocalDate;

public record DateRange(LocalDate startDate, LocalDate endDate) {
    public DateRange {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Дати не можуть бути порожніми.");
        }
        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new IllegalArgumentException("Дата початку має бути раніше за дату завершення.");
        }
    }

    public boolean overlaps(DateRange other) {
        return this.startDate.isBefore(other.endDate) && this.endDate.isAfter(other.startDate);
    }
}