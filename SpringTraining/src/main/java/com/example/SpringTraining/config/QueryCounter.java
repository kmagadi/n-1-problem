package com.example.SpringTraining.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Component;

@Component
public class QueryCounter {

    private final Statistics statistics;

    public QueryCounter(EntityManagerFactory entityManagerFactory) {
        this.statistics = entityManagerFactory.unwrap(org.hibernate.SessionFactory.class).getStatistics();
    }

    public void reset() {
        statistics.clear();
    }

    public long getQueryCount() {
        return statistics.getPrepareStatementCount();
    }
}