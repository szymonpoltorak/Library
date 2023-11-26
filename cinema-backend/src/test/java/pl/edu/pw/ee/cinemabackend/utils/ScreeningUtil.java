package pl.edu.pw.ee.cinemabackend.utils;

import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ScreeningUtil {

    public static LocalDate checkIfValidDate(CharSequence stringDate) {
        LocalDate date;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;

        try {
            date = LocalDate.parse(stringDate, dateFormatter);
        } catch (DateTimeParseException e) {
            date = null;
        }

        return date;
    }

    public static LocalTime checkIfValidTime(CharSequence stringTime) {
        LocalTime time;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_TIME;

        try {
            time = LocalTime.parse(stringTime, timeFormatter);
        } catch (DateTimeParseException e) {
            time = null;
        }

        return time;
    }

    public static User createAdminUser() {
        return User.builder()
                .username("kicia2@mail.com")
                .name("John")
                .surname("Doe")
                .password("kicia.?312312312As")
                .userRole(UserRole.ADMIN)
                .build();
    }

    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate YESTERDAY = TODAY.minusDays(1);
    public static final LocalDate TOMORROW = TODAY.plusDays(1);

    public static LocalDate relativeDayToLocalDate(String relativeDay) {
        return switch (relativeDay) {
            case "today" -> TODAY;
            case "yesterday" -> YESTERDAY;
            case "tomorrow" -> TOMORROW;
            default -> throw new IllegalArgumentException("No relative day mapping is defined for "+relativeDay);
        };
    }

}
