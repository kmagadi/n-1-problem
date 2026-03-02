package com.example.SpringTraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SpringTrainingApplication - Main Entry Point
 *
 * This is the orchestration point for the Spring Boot application that manages
 * Customer CRUD operations using Spring Data JPA and REST APIs.
 *
 * Features:
 * - RESTful API endpoints for Customer management
 * - H2 in-memory database with automatic schema creation
 * - JPA/Hibernate for Object-Relational Mapping
 * - Spring Boot auto-configuration
 *
 * Access Points:
 * - API Base: http://localhost:8080/api
 * - Customer Endpoints: http://localhost:8080/api/customers
 * - H2 Console: http://localhost:8080/api/h2-console
 *
 * @author Spring Training Team
 * @version 1.0
 */
@SpringBootApplication
public class SpringTrainingApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringTrainingApplication.class);

	public static void main(String[] args) {
		// Start the Spring Boot application
		ConfigurableApplicationContext context = SpringApplication.run(SpringTrainingApplication.class, args);

		// Log application startup information
		logApplicationStartupInfo(context);
	}

	/**
	 * Log application startup information including available endpoints and configuration
	 *
	 * @param context The application context
	 */
	private static void logApplicationStartupInfo(ConfigurableApplicationContext context) {
		String applicationName = context.getEnvironment().getProperty("spring.application.name");
		String port = context.getEnvironment().getProperty("server.port", "8080");
		String contextPath = context.getEnvironment().getProperty("server.servlet.context-path", "");
		String baseUrl = "http://localhost:" + port + contextPath;

		logger.info("=====================================================");
		logger.info("✓ {} Application Started Successfully!", applicationName);
		logger.info("=====================================================");
		logger.info("");
		logger.info("📍 Application is running at: {}", baseUrl);
		logger.info("");
		logger.info("Available Endpoints:");
		logger.info("  • GET    {}/customers              - Get all customers");
		logger.info("  • GET    {}/customers/{{id}}       - Get customer by ID");
		logger.info("  • POST   {}/customers              - Create new customer");
		logger.info("  • PUT    {}/customers/{{id}}       - Update customer");
		logger.info("  • DELETE {}/customers/{{id}}       - Delete customer");
		logger.info("");
		logger.info("  • GET    {}/departments            - Get all departments with students and course counts");
		logger.info("");
		logger.info("Database Access:");
		logger.info("  • H2 Console: {}/h2-console", baseUrl);
		logger.info("  • Database URL: jdbc:h2:mem:testdb");
		logger.info("  • Username: sa");
		logger.info("  • Password: (empty)");
		logger.info("");
		logger.info("API Documentation:");
		logger.info("  • Base URL: {}", baseUrl);
		logger.info("  • Content-Type: application/json");
		logger.info("");
		logger.info("=====================================================");
		logger.info("Ready to accept requests!");
		logger.info("=====================================================");
	}

}
