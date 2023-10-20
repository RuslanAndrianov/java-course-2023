package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")
public final class Task1 {

    private static final int SECONDS_IN_MINUTE = 60;

    public static int minutesToSeconds(String time) {

        if (time != null) {

            // Ignore spaces
            String timeWithoutSpaces = time.replace(" ", "");

            // Max valid input: "99999:59"
            String timeRegex = "0*\\d{2,5}:[0-5]\\d";

            if (timeWithoutSpaces.matches(timeRegex)) {
                String[] minsAndSecs = timeWithoutSpaces.split(":");
                int minutes = Integer.parseInt(minsAndSecs[0]);
                int seconds = Integer.parseInt(minsAndSecs[1]);
                return (seconds + minutes * SECONDS_IN_MINUTE);
            }
        }

        return -1;
    }
}
