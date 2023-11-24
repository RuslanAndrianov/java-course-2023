package edu.project3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@SuppressWarnings({"HideUtilityClassConstructor", "MagicNumber", "CyclomaticComplexity"})
public class HelpFunctions {

    public static boolean isWithinRange(LogRecord logRecord, String fromArg, String toArg) {
        String datePattern = "yyyy-MM-dd";
        LocalDate from = (fromArg != null) ? LocalDate.parse(fromArg, DateTimeFormatter.ofPattern(datePattern)) : null;
        LocalDate to = (toArg != null) ? LocalDate.parse(toArg, DateTimeFormatter.ofPattern(datePattern)) : null;

        return (from == null || logRecord.dateTime().isAfter(from.atStartOfDay())
            || logRecord.dateTime().isEqual(from.atStartOfDay()))
            && (to == null || logRecord.dateTime().isBefore(to.atStartOfDay())
            || logRecord.dateTime().isEqual(to.atStartOfDay()));
    }

    public static LogReport generateLogReport(
        Stream<LogRecord> logRecordsStream, String pathArg,
        String fromDate, String toDate) {
        LogReport logReport = new LogReport(pathArg, fromDate, toDate);
        logRecordsStream.forEach(logRecord -> {
            logReport.incrementTotalRequests();
            logReport.addResource(logRecord.requestUri());
            logReport.addStatusCode(logRecord.statusCode());
            logReport.addResponseSize(logRecord.responseSize());
        });
        return logReport;
    }

    public static String getStatusCodeName(int statusCode) {
        return switch (statusCode) {
            case 100 -> "Continue";
            case 101 -> "Switching Protocols";
            case 102 -> "Processing";
            case 103 -> "Early Hints";
            case 200 -> "OK";
            case 201 -> "Created";
            case 202 -> "Accepted";
            case 203 -> "Non-Authoritative Information";
            case 204 -> "No Content";
            case 205 -> "Reset Content";
            case 206 -> "Partial Content";
            case 207 -> "Multi-Status";
            case 208 -> "Already Reported";
            case 226 -> "IM Used";
            case 300 -> "Multiple Choices";
            case 301 -> "Moved Permanently";
            case 302 -> "Moved Temporarily";
            case 303 -> "See Other";
            case 304 -> "Not Modified";
            case 305 -> "Use Proxy";
            case 307 -> "Temporary Redirect";
            case 308 -> "Permanent Redirect";
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 402 -> "Payment Required";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            case 406 -> "Not Acceptable";
            case 407 -> "Proxy Authentication Required";
            case 408 -> "Request Timeout";
            case 409 -> "Conflict";
            case 410 -> "Gone";
            case 411 -> "Length Required";
            case 412 -> "Precondition Failed";
            case 413 -> "Payload Too Large";
            case 414 -> "URI Too Long";
            case 415 -> "Unsupported Media Type";
            case 416 -> "Range Not Satisfiable";
            case 417 -> "Expectation Failed";
            case 418 -> "I’m a teapot";
            case 419 -> "Authentication Timeout";
            case 421 -> "Misdirected Request";
            case 422 -> "Unprocessable Entity";
            case 423 -> "Locked";
            case 424 -> "Failed Dependency";
            case 425 -> "Too Early";
            case 426 -> "Upgrade Required";
            case 428 -> "Precondition Required";
            case 429 -> "Too Many Requests";
            case 431 -> "Request Header Fields Too Large";
            case 449 -> "Retry With";
            case 451 -> "Unavailable For Legal Reasons";
            case 499 -> "Client Closed Request";
            case 500 -> "Internal Server Error";
            case 501 -> "Not Implemented";
            case 502 -> "Bad Gateway";
            case 503 -> "Service Unavailable";
            case 504 -> "Gateway Timeout";
            case 505 -> "HTTP Version Not Supported";
            case 506 -> "Variant Also Negotiates";
            case 507 -> "Insufficient Storage";
            case 508 -> "Loop Detected";
            case 509 -> "Bandwidth Limit Exceeded";
            case 510 -> "Not Extended";
            case 511 -> "Network Authentication Required";
            case 520 -> "Unknown Error";
            case 521 -> "Web Server Is Down";
            case 522 -> "Connection Timed Out";
            case 523 -> "Origin Is Unreachable";
            case 524 -> "A Timeout Occurred";
            case 525 -> "SSL Handshake Failed";
            case 526 -> "Invalid SSL Certificate";
            default -> "Unknown code";
        };
    }
}
