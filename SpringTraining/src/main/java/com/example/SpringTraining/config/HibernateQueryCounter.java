package com.example.SpringTraining.config;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Component;

@Component
public class HibernateQueryCounter {

    private final Statistics statistics;

    public HibernateQueryCounter(EntityManager entityManager) {
        SessionFactory sessionFactory = entityManager
                .unwrap(org.hibernate.Session.class)
                .getSessionFactory();
        this.statistics = sessionFactory.getStatistics();
    }

    public void reset() {
        statistics.clear();
    }

    public long getQueryCount() {
        return statistics.getPrepareStatementCount();
    }
}