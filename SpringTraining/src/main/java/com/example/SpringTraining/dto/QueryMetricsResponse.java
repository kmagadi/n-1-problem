package com.example.SpringTraining.dto;

import java.util.List;

public class QueryMetricsResponse<T> {

    private List<T> data;
    private long queryCount;
    private long executionTimeMs;

    public QueryMetricsResponse(List<T> data, long queryCount, long executionTimeMs) {
        this.data = data;
        this.queryCount = queryCount;
        this.executionTimeMs = executionTimeMs;
    }

    public List<T> getData() {
        return data;
    }

    public long getQueryCount() {
        return queryCount;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }
}