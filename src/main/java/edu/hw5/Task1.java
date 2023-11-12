package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"HideUtilityClassConstructor", "MagicNumber"})
public class Task1 {

    public static @NotNull String calculateAvgTime(@NotNull List<String> times) {

        if (times.isEmpty()) {
            return "0ч 00м";
        }

        long totalTime = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

        for (String time : times) {
            String[] interval = time.split(" - ");
            LocalDateTime start = LocalDateTime.parse(interval[0], formatter);
            LocalDateTime end = LocalDateTime.parse(interval[1], formatter);
            Duration duration = Duration.between(start, end);
            totalTime += duration.getSeconds();
        }

        long avgTime = totalTime / times.size();
        Duration avgDuration = Duration.ofSeconds(avgTime);
        long avgHours = avgDuration.toHours();
        long avgMinutes = avgDuration.minusHours(avgHours).toMinutes();

        if (avgMinutes >= 10) {
            return (avgHours + "ч " + avgMinutes + "м");
        } else {
            return (avgHours + "ч 0" + avgMinutes + "м");
        }
    }
}
