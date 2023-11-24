package edu.project3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LogReport {

    public final List<String> resources = new ArrayList<>();

    public final Map<Integer, Integer> statusCodes = new HashMap<>();

    public final List<Long> responseSizes = new ArrayList<>();

    public int totalRequests = 0;

    public final String pathArg;

    public final String fromDate;

    public final String toDate;

    public LogReport(String pathArg, String fromDate, String toDate) {
        this.pathArg = pathArg;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public void addResource(String resource) {
        resources.add(resource);
    }

    public void addStatusCode(int statusCode) {
        statusCodes.put(statusCode, statusCodes.getOrDefault(statusCode, 0) + 1);
    }

    public void addResponseSize(long responseSize) {
        responseSizes.add(responseSize);
    }

    public void incrementTotalRequests() {
        totalRequests++;
    }

    public String getFromDate() {
        return Objects.requireNonNullElse(fromDate, "--");
    }

    public String getToDate() {
        return Objects.requireNonNullElse(toDate, "--");
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public String getAverageResponseSize() {
        if (responseSizes.isEmpty()) {
            return "0";
        }
        long sum = responseSizes.stream().mapToLong(Long::longValue).sum();
        return String.valueOf(sum / responseSizes.size());
    }

    public Map<String, Integer> getResources() {
        Map<String, Integer> resourceCounts = new HashMap<>();
        for (String resource : resources) {
            resourceCounts.put(resource, resourceCounts.getOrDefault(resource, 0) + 1);
        }
        return resourceCounts;
    }

    public Map<Integer, Integer> getStatusCodes() {
        return statusCodes;
    }
}
