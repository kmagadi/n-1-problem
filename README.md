# Spring Boot JPA Performance Optimization – N+1 Query Problem

## Overview

This project demonstrates different strategies to identify, solve, and benchmark the N+1 query problem in a Spring Boot application using Spring Data JPA and Hibernate.

The application models a simple University domain consisting of Departments, Students, and Courses. The goal is to understand how ORM frameworks behave with nested relationships and how different fetching strategies impact database performance.

The project focuses on:
- Identifying the N+1 query problem
- Implementing multiple optimization strategies
- Measuring query count and execution time
- Comparing performance trade-offs
- Understanding real-world ORM behavior

---

## Domain Model

The system consists of the following relationships:

- One Department has many Students
- One Student belongs to one Department
- One Student has many Courses
- One Course can belong to many Students

This many-to-many and one-to-many relationship combination commonly leads to performance issues when fetching hierarchical data.

---

## Problem Statement

When using lazy loading in JPA, fetching nested relationships results in multiple SQL queries:

1 query for Departments
+ N queries for Students
+ N*M queries for Courses

This leads to:
- Increased latency
- Excessive database load
- Poor scalability

This issue is known as the N+1 query problem.

---

## Optimization Strategies Implemented

This project implements and benchmarks the following strategies:

### 1. Fetch Join (JOIN FETCH)

Uses JPQL with LEFT JOIN FETCH to retrieve the entire object graph in a single query.

Advantages:
- Eliminates the N+1 problem
- Simple to implement
- High performance for small to medium datasets

Limitations:
- Can produce large result sets
- Higher memory consumption
- Not ideal for pagination

---

### 2. Entity Graph

Uses the @EntityGraph annotation to declaratively control fetching.

Advantages:
- Cleaner and reusable
- Flexible fetch configuration
- Optimized SQL similar to fetch join
- Supports dynamic query planning

---

### 3. Lazy Loading with Batch Fetching(YET TO IMPLEMENT)

Keeps relationships lazy but reduces query count using Hibernate batch fetching.

Advantages:
- Balanced performance
- Efficient for large datasets
- Avoids loading unnecessary data
- Reduces query explosion

---

### 4. DTO Projection(YET TO IMPLEMENT)

Fetches only required data using projections instead of full entity loading.

Advantages:
- Lowest memory usage
- High performance
- Avoids entity overhead
- Ideal for read-heavy APIs and microservices

---

### 5. Pagination-Based Fetching(YET TO IMPLEMENT)

Splits large queries into smaller pages.

Advantages:
- Scalable for large datasets
- Reduces memory pressure
- Improves response time

---

## Query Monitoring

A custom query counter is implemented using Hibernate statistics.

The system tracks:
- Total SQL queries executed
- Execution time per request
- Strategy-level performance

Each API response includes:
- Data
- Query count
- Execution time

This allows real-time benchmarking and performance comparison.

---

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Data JPA
- Hibernate 6
- H2 In-Memory Database
- Maven

---

## Project Structure

src/main/java/com/example/SpringTraining

config
- HibernateQueryCounter

controller
- DepartmentController

dto
- DepartmentDTO
- StudentDTO
- QueryMetricsResponse

entities
- Department
- Student
- Course

repositories
- DepartmentRepository

service
- DepartmentService

ApplicationConfig

---

## API Endpoints

Fetch Join
GET /api/departments/fetch-join

Entity Graph
GET /api/departments/entity-graph

Batch Fetching
GET /api/departments/batch

DTO Projection
GET /api/departments/dto

Pagination
GET /api/departments/pagination

Each endpoint returns:

{
  "data": [...],
  "queryCount": X,
  "executionTimeMs": Y
}

---

## Sample Response

{
  "data": [
    {
      "departmentName": "Computer Science",
      "students": [
        {
          "studentName": "Rahul",
          "coursesCount": 5
        }
      ]
    }
  ],
  "queryCount": 1,
  "executionTimeMs": 25
}

---

## Performance Comparison

Strategy | Query Count | Performance
-------- | ----------- | -----------
Lazy (default) | High | Poor
Fetch Join | Low | Good
Entity Graph | Low | Good
Batch Fetching | Medium | Balanced
DTO Projection | Low | Best
Pagination | Low | Scalable

---

## How to Run

Step 1: Clone the repository

git clone <repo-url>
cd SpringTraining

Step 2: Build and run

mvn clean install
mvn spring-boot:run

Step 3: Access the application

http://localhost:8080

H2 Console:

http://localhost:8080/h2-console

Database:

jdbc:h2:mem:testdb
username: sa
password: (empty)

---

## Future Enhancements

- Load testing with large datasets
- PostgreSQL and MySQL benchmarking
- Redis caching integration
- Monitoring dashboards
- Reactive Spring Boot version
- GraphQL implementation
- Cloud deployment and scaling

---

## Key Learnings

- ORM performance pitfalls
- Hibernate fetch strategies
- Lazy vs eager loading trade-offs
- Real-world database tuning
- Clean DTO-based architecture
- Scalable backend design

---

## Author

Karthik B Magadi
