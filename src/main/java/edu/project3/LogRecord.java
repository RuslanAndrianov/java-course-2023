package edu.project3;

import java.time.LocalDateTime;

public record LogRecord(LocalDateTime dateTime, int statusCode, int responseSize, String requestUri) { }
