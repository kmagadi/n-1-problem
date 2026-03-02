package com.example.SpringTraining;

import com.example.SpringTraining.entites.Course;
import com.example.SpringTraining.entites.Department;
import com.example.SpringTraining.entites.Student;
import com.example.SpringTraining.repositories.CourseRepository;
import com.example.SpringTraining.repositories.DepartmentRepository;
import com.example.SpringTraining.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Application Configuration and Data Initialization
 *
 * Initializes sample data for University Management System on application startup
 */
@Configuration
public class ApplicationConfig {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Initialize sample data on application startup
     * Creates departments, students, and courses with relationships
     */
    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            // Clear existing data
            studentRepository.deleteAll();
            departmentRepository.deleteAll();
            courseRepository.deleteAll();

            System.out.println("\n==================== DATA INITIALIZATION ====================");

            // Initialize University Management System data
            initializeUniversityData();

            System.out.println("✓ Data initialization completed!");
            System.out.println("==========================================================\n");
        };
    }

    /**
     * Initialize Department, Student, and Course data
     *
     * Creates:
     * - 2 Departments (Computer Science, Physics)
     * - 5 Students with course assignments
     * - 8 Courses
     */
    private void initializeUniversityData() {
        System.out.println("\n📚 Initializing courses...");

        // Create Courses
        Course course1 = new Course("Data Structures");
        Course course2 = new Course("Algorithms");
        Course course3 = new Course("Database Systems");
        Course course4 = new Course("Web Development");
        Course course5 = new Course("Machine Learning");
        Course course6 = new Course("Physics I");
        Course course7 = new Course("Physics II");
        Course course8 = new Course("Quantum Mechanics");

        courseRepository.saveAll(Arrays.asList(course1, course2, course3, course4, course5, course6, course7, course8));
        System.out.println("✓ Created 8 courses");

        System.out.println("\n🏢 Initializing departments...");

        // Create Departments
        Department csDept = new Department("Computer Science");
        Department physicsDept = new Department("Physics");

        departmentRepository.saveAll(Arrays.asList(csDept, physicsDept));
        System.out.println("✓ Created 2 departments");

        System.out.println("\n👥 Initializing students and enrolling in courses...");

        // Create Students for Computer Science Department
        Student rahul = new Student("Rahul");
        rahul.setDepartment(csDept);
        rahul.setCourses(Arrays.asList(course1, course2, course3, course4, course5));

        Student priya = new Student("Priya");
        priya.setDepartment(csDept);
        priya.setCourses(Arrays.asList(course1, course2, course3));

        Student amit = new Student("Amit");
        amit.setDepartment(csDept);
        amit.setCourses(Arrays.asList(course1, course4, course5));

        // Create Students for Physics Department
        Student neha = new Student("Neha");
        neha.setDepartment(physicsDept);
        neha.setCourses(Arrays.asList(course6, course7, course8));

        Student vikram = new Student("Vikram");
        vikram.setDepartment(physicsDept);
        vikram.setCourses(Arrays.asList(course6, course7));

        studentRepository.saveAll(Arrays.asList(rahul, priya, amit, neha, vikram));
        System.out.println("✓ Created 5 students");

        System.out.println("\n📊 Database Summary:");
        System.out.println("  • Total Departments: " + departmentRepository.count());
        System.out.println("  • Total Students: " + studentRepository.count());
        System.out.println("  • Total Courses: " + courseRepository.count());

        System.out.println("\n📍 Computer Science Department:");
        System.out.println("  • Rahul - Enrolled in 5 courses");
        System.out.println("  • Priya - Enrolled in 3 courses");
        System.out.println("  • Amit - Enrolled in 3 courses");

        System.out.println("\n📍 Physics Department:");
        System.out.println("  • Neha - Enrolled in 3 courses");
        System.out.println("  • Vikram - Enrolled in 2 courses");
    }
}

