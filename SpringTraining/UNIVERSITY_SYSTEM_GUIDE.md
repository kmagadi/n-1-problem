# University Management System - Complete Setup & Testing Guide

## ✅ Project Structure

Your Spring Boot project is now fully structured with the following organization:

```
SpringTraining/
├── src/main/java/com/example/SpringTraining/
│   ├── SpringTrainingApplication.java          (Main entry point)
│   ├── ApplicationConfig.java                  (Initialization & sample data)
│   │
│   ├── controller/
│   │   └── DepartmentController.java           (REST API endpoints)
│   │
│   ├── service/
│   │   └── DepartmentService.java              (Business logic)
│   │
│   ├── dto/
│   │   ├── DepartmentDTO.java                  (Data Transfer Object)
│   │   └── StudentDTO.java                     (Data Transfer Object)
│   │
│   ├── entites/
│   │   ├── Department.java                     (JPA Entity - @OneToMany)
│   │   ├── Student.java                        (JPA Entity - @ManyToOne, @ManyToMany)
│   │   └── Course.java                         (JPA Entity - @ManyToMany)
│   │
│   └── repositories/
│       ├── DepartmentRepository.java           (Spring Data JPA)
│       ├── StudentRepository.java              (Spring Data JPA)
│       └── CourseRepository.java               (Spring Data JPA)
│
├── src/main/resources/
│   └── application.properties                  (Configuration)
│
└── pom.xml                                     (Maven dependencies)
```

---

## 📊 Database Schema

### Departments Table
```sql
CREATE TABLE departments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(255) NOT NULL
);
```

### Students Table
```sql
CREATE TABLE students (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(255) NOT NULL,
    department_id BIGINT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);
```

### Courses Table
```sql
CREATE TABLE courses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(255) NOT NULL
);
```

### Student-Course Junction Table (Many-to-Many)
```sql
CREATE TABLE student_courses (
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);
```

---

## 🔗 Entity Relationships

```
Department (1) ──────────────── (Many) Students
                   OneToMany

Student (Many) ─────────────── (Many) Courses
             ManyToMany
```

**Relationships Summary:**
- **Department → Students**: One-to-Many (A department has many students)
- **Students → Courses**: Many-to-Many (A student can take many courses, and a course can have many students)
- **Courses → Students**: Many-to-Many (inverse side)

---

## 📦 Sample Data Initialized

### Computer Science Department
- **Rahul** - Enrolled in 5 courses (Data Structures, Algorithms, Database Systems, Web Development, Machine Learning)
- **Priya** - Enrolled in 3 courses (Data Structures, Algorithms, Database Systems)
- **Amit** - Enrolled in 3 courses (Data Structures, Web Development, Machine Learning)

### Physics Department
- **Neha** - Enrolled in 3 courses (Physics I, Physics II, Quantum Mechanics)
- **Vikram** - Enrolled in 2 courses (Physics I, Physics II)

---

## 🚀 Quick Start

### 1. Build the Project
```bash
cd C:\Accolite\Training Agenda\02-03-2026, Spring Training\SpringTraining\SpringTraining
.\mvnw.cmd clean package -DskipTests
```

### 2. Run the Application
```bash
.\mvnw.cmd spring-boot:run
```

### 3. Access the Application
```
Base URL: http://localhost:8080/api
H2 Console: http://localhost:8080/api/h2-console
```

---

## 🔌 API Endpoints

### GET /api/departments

**Retrieves all departments with their students and course counts**

**URL:** `http://localhost:8080/api/departments`

**Method:** GET

**Response Example:**
```json
[
  {
    "departmentName": "Computer Science",
    "students": [
      {
        "studentName": "Rahul",
        "coursesCount": 5
      },
      {
        "studentName": "Priya",
        "coursesCount": 3
      },
      {
        "studentName": "Amit",
        "coursesCount": 3
      }
    ]
  },
  {
    "departmentName": "Physics",
    "students": [
      {
        "studentName": "Neha",
        "coursesCount": 3
      },
      {
        "studentName": "Vikram",
        "coursesCount": 2
      }
    ]
  }
]
```

**Status Code:** 200 OK

---

## 🧪 Testing with cURL

### PowerShell
```powershell
# Test the endpoint
curl http://localhost:8080/api/departments

# Pretty print JSON response
curl http://localhost:8080/api/departments | ConvertFrom-Json | ConvertTo-Json
```

### Bash/Git Bash
```bash
# Test the endpoint
curl http://localhost:8080/api/departments

# Pretty print with jq
curl http://localhost:8080/api/departments | jq .
```

---

## 🧪 Testing with Postman

1. **Create New Request**
   - Method: GET
   - URL: http://localhost:8080/api/departments
   - Click "Send"

2. **View Response**
   - Response body will show all departments with nested students and their course counts

---

## 📋 Class Descriptions

### Entity Classes (entites/)

#### Department.java
- Represents a university department
- Has a one-to-many relationship with Students
- Annotations: @Entity, @Table, @OneToMany

#### Student.java
- Represents a student enrolled in a department
- Has a many-to-one relationship with Department
- Has a many-to-many relationship with Course
- Annotations: @Entity, @ManyToOne, @ManyToMany, @JoinTable

#### Course.java
- Represents a course offered
- Has a many-to-many relationship with Student (inverse side)
- Annotations: @Entity, @ManyToMany

### DTO Classes (dto/)

#### DepartmentDTO
- Data Transfer Object for Department
- Contains: departmentName, List<StudentDTO>
- Used for API response formatting

#### StudentDTO
- Data Transfer Object for Student
- Contains: studentName, coursesCount
- Used for API response formatting (only student name and course count)

### Service Classes (service/)

#### DepartmentService
- Business logic layer
- Method: `getAllDepartments()` - Retrieves all departments with students and courses
- Converts Entity objects to DTOs
- Solves N+1 query problem using LEFT JOIN FETCH

### Repository Classes (repositories/)

#### DepartmentRepository
- Spring Data JPA interface
- Custom query: `findAllWithStudentsAndCourses()`
- Uses LEFT JOIN FETCH to avoid N+1 queries
- Extends: JpaRepository<Department, Long>

#### StudentRepository
- Spring Data JPA interface
- Extends: JpaRepository<Student, Long>

#### CourseRepository
- Spring Data JPA interface
- Extends: JpaRepository<Course, Long>

### Controller Classes (controller/)

#### DepartmentController
- REST API Controller
- Endpoint: GET /api/departments
- Uses @RestController and @RequestMapping annotations
- Calls DepartmentService to fetch data
- Returns ResponseEntity with List<DepartmentDTO>

---

## 🔍 N+1 Query Problem Solution

### Problem
Without optimization, fetching departments with students and courses would result in:
- 1 query to fetch all departments
- N queries to fetch students for each department
- M queries to fetch courses for each student
= N+1+M queries (inefficient)

### Solution
The `DepartmentRepository.findAllWithStudentsAndCourses()` uses:
```java
@Query("SELECT DISTINCT d FROM Department d 
        LEFT JOIN FETCH d.students s 
        LEFT JOIN FETCH s.courses")
```

This uses **LEFT JOIN FETCH** to:
1. Fetch all departments with their students in a single query
2. Fetch all courses for those students in another single query
3. Total: 2 queries instead of N+1+M queries

---

## 🛠 Configuration

### application.properties
```properties
spring.application.name=SpringTraining
server.port=8080
server.servlet.context-path=/api

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.h2.console.enabled=true
```

---

## 📊 Data Flow Diagram

```
HTTP Request (GET /api/departments)
         │
         ▼
DepartmentController.getDepartments()
         │
         ▼
DepartmentService.getAllDepartments()
         │
         ▼
DepartmentRepository.findAllWithStudentsAndCourses()
         │
         ▼
H2 Database (Single optimized query with LEFT JOIN FETCH)
         │
         ▼
List<Department> (with Students and Courses loaded)
         │
         ▼
Convert to List<DepartmentDTO>
         │
         ▼
Return JSON Response to Client
```

---

## ✅ Verification Checklist

After running the application:

- [ ] Application starts without errors
- [ ] Data initialization completes successfully
- [ ] H2 Console is accessible at http://localhost:8080/api/h2-console
- [ ] GET /api/departments returns 200 OK status
- [ ] Response contains 2 departments (Computer Science, Physics)
- [ ] Computer Science department has 3 students
- [ ] Physics department has 2 students
- [ ] Each student shows correct course count
- [ ] No N+1 query issues (only 2 queries in database logs)

---

## 🐛 Troubleshooting

### Port 8080 Already in Use
```bash
# Find process using port 8080
netstat -ano | findstr :8080

# Kill the process
taskkill /PID <PID> /F

# Or change port in application.properties
server.port=9090
```

### No Data Showing
- Check ApplicationConfig.initializeData() is being called
- Verify all repositories are properly autowired
- Check database logs in application console

### 404 Error on /api/departments
- Ensure DepartmentController is in the right package (com.example.SpringTraining.controller)
- Verify @RestController and @RequestMapping annotations
- Check context path in application.properties

---

## 📚 Learning Resources

This project demonstrates:
- ✅ Spring Boot REST API development
- ✅ JPA Entity relationships (One-to-Many, Many-to-Many)
- ✅ Spring Data JPA repositories with custom queries
- ✅ Data Transfer Objects (DTO pattern)
- ✅ Service layer architecture
- ✅ Solving N+1 query problems
- ✅ Proper package organization
- ✅ H2 in-memory database
- ✅ Automated data initialization

---

## 📝 Summary

Your University Management System is now:
- ✅ **Fully structured** with proper package organization
- ✅ **Complete** with all required entities, repositories, services, and controllers
- ✅ **Optimized** using LEFT JOIN FETCH to solve N+1 query issues
- ✅ **Pre-populated** with sample data for testing
- ✅ **Ready to test** the GET /api/departments endpoint

**Next Steps:**
1. Run `.\mvnw.cmd spring-boot:run`
2. Wait for application to start
3. Test the endpoint: `curl http://localhost:8080/api/departments`
4. Verify the response matches the expected format


