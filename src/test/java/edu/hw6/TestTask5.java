package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task5.HackerNews.hackerNewsTopStories;
import static edu.hw6.Task5.HackerNews.news;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask5 {

    @Test
    @DisplayName("Тест hackerNewsTopStories")
    void testHackerNewsTopStories() {
        long[] newsIDs = hackerNewsTopStories();
        assertTrue(newsIDs.length > 400);
    }

    @Test
    @DisplayName("Тест news")
    void testNews() {
        assertEquals("JDK 21 Release Notes", news(37570037));
    }
}
