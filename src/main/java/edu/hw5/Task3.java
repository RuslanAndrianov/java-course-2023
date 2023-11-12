package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {

    private static final String FORMAT1 = "^\\d{4}-\\d{1,2}-\\d{1,2}$";

    private static final String FORMAT2 = "^\\d{1,2}/\\d{1,2}/\\d{2,4}$";

    private static final String FORMAT3 = "^\\d+ days ago$";

    private static final int HUNDRED = 100;

    private static final int SECOND_MILLENIUM = 2000;

    public static Optional<LocalDate> parseDate(@NotNull String string) {

        if (string.matches(FORMAT1)) {
            String[] parts = string.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            return Optional.of(LocalDate.of(year, month, day));
        }
        if (string.matches(FORMAT2)) {
            String[] parts = string.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            if (year < HUNDRED) {
                year += SECOND_MILLENIUM;
            }
            return Optional.of(LocalDate.of(year, month, day));
        }
        if (string.matches(FORMAT3)) {
            long days = Long.parseLong(string.split(" ")[0]);
            return Optional.of(LocalDate.now().minusDays(days));
        }
        return switch (string) {
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            case "today" -> Optional.of(LocalDate.now());
            case "yesterday", "1 day ago" -> Optional.of(LocalDate.now().minusDays(1));
            default -> Optional.empty();
        };
    }
}
