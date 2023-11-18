package edu.hw6.Task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import static java.time.temporal.ChronoUnit.SECONDS;

@SuppressWarnings("HideUtilityClassConstructor")
public class HackerNews {

    private static final int MAXIMUM_DELAY = 10;

    public static long @NotNull [] hackerNewsTopStories() {

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .timeout(Duration.of(MAXIMUM_DELAY, SECONDS))
            .GET()
            .build();

        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String[] newsIDs = response
                .body()
                .replaceAll("\\[", "")
                .replaceAll("]", "")
                .split(",");

            long[] result = new long[newsIDs.length];

            for (int i = 0; i < newsIDs.length; i++) {
                result[i] = Long.parseLong(newsIDs[i]);
            }
            return result;

        } catch (Exception e) {
            return new long[]{};
        }
    }

    public static @NotNull String news(long id) {

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .timeout(Duration.of(MAXIMUM_DELAY, SECONDS))
            .GET()
            .build();

        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Pattern title = Pattern.compile(".*\"title\":\"(.+?)\".*");
            Matcher matcher = title.matcher(response.body());

            if (matcher.find()) {
                return matcher.group(1);
            }
            return "";

        } catch (Exception e) {
            return "";
        }
    }
}
