package edu.hw5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import static java.time.DayOfWeek.FRIDAY;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {

    private static final int MONTHS_IN_YEAR = 12;

    private static final int BAKERS_DOZEN = 13;

    public static @NotNull List<LocalDate> findAllFridays13(int year) {

        List<LocalDate> fridays13 = new ArrayList<>();
        for (int month = 1; month <= MONTHS_IN_YEAR; month++) {
            LocalDate localDate = LocalDate.of(year, month, BAKERS_DOZEN);
            if (localDate.getDayOfWeek() == FRIDAY) {
                fridays13.add(localDate);
            }
        }
        return fridays13;
    }

    public static LocalDate findClosestFriday13(@NotNull LocalDate date) {
        LocalDate nextFriday13;
        if (date.getDayOfMonth() < BAKERS_DOZEN) {
            nextFriday13 = date.withDayOfMonth(BAKERS_DOZEN);
        } else {
            nextFriday13 = date.plusMonths(1).withDayOfMonth(BAKERS_DOZEN);
        }
        while (nextFriday13.getDayOfWeek() != FRIDAY) {
            nextFriday13 = nextFriday13.plusMonths(1);
        }
        return nextFriday13;
    }
}
