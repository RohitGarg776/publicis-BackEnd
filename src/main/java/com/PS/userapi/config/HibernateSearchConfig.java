package com.PS.userapi.config;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component

public class HibernateSearchConfig {

    private static final Logger log = LoggerFactory.getLogger(HibernateSearchConfig.class);
    private final EntityManager entityManager;
    public HibernateSearchConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Async
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initializeHibernateSearch() {
        try {
            log.info("Starting database initialization");

            // Basic database connection check
            entityManager.createQuery("SELECT 1").getSingleResult();
            log.info("Database connection verified");

            log.info("Basic initialization completed successfully");

        } catch (Exception e) {
            log.error("Initialization failed", e);
            throw new RuntimeException("Failed to initialize application", e);
        }
    }
}