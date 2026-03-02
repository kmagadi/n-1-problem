package com.example.SpringTraining.config;

import com.example.SpringTraining.entites.Course;
import com.example.SpringTraining.entites.Department;
import com.example.SpringTraining.entites.Student;
import com.example.SpringTraining.repositories.CourseRepository;
import com.example.SpringTraining.repositories.DepartmentRepository;
import com.example.SpringTraining.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Application Configuration and Data Initialization
 */
@Configuration
public class ApplicationConfig {

    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public ApplicationConfig(DepartmentRepository departmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * Initialize sample data on application startup
     */
    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            System.out.println("\n==================== DATA INITIALIZATION ====================");
            initializeUniversityData();
            System.out.println("✓ Data initialization completed!");
            System.out.println("==========================================================\n");
        };
    }

    /**
     * Transactional ensures entities remain managed
     */
    @Transactional
    public void initializeUniversityData() {

        // Order matters due to FK constraints
        studentRepository.deleteAll();
        departmentRepository.deleteAll();
        courseRepository.deleteAll();

        System.out.println("\n📚 Initializing courses...");

        List<Course> courses = courseRepository.saveAll(List.of(
                new Course("Data Structures"),
                new Course("Algorithms"),
                new Course("Database Systems"),
                new Course("Web Development"),
                new Course("Machine Learning"),
                new Course("Physics I"),
                new Course("Physics II"),
                new Course("Quantum Mechanics")
        ));

        System.out.println("✓ Created 8 courses");

        System.out.println("\n🏢 Initializing departments...");

        Department csDept = new Department("Computer Science");
        Department physicsDept = new Department("Physics");

        departmentRepository.saveAll(List.of(csDept, physicsDept));

        System.out.println("✓ Created 2 departments");

        System.out.println("\n👥 Initializing students and enrolling in courses...");

        // Computer Science Students
        Student rahul = new Student("Rahul");
        rahul.setDepartment(csDept);
        rahul.setCourses(new HashSet<>(courses.subList(0, 5)));

        Student priya = new Student("Priya");
        priya.setDepartment(csDept);
        priya.setCourses(new HashSet<>(courses.subList(0, 3)));

        Student amit = new Student("Amit");
        amit.setDepartment(csDept);
        amit.setCourses(Set.of(
                courses.get(0),
                courses.get(3),
                courses.get(4)
        ));

        // Physics Students
        Student neha = new Student("Neha");
        neha.setDepartment(physicsDept);
        neha.setCourses(new HashSet<>(courses.subList(5, 8)));

        Student vikram = new Student("Vikram");
        vikram.setDepartment(physicsDept);
        vikram.setCourses(Set.of(
                courses.get(5),
                courses.get(6)
        ));

        studentRepository.saveAll(List.of(rahul, priya, amit, neha, vikram));

        System.out.println("✓ Created 5 students");

        System.out.println("\n📊 Database Summary:");
        System.out.println("  • Total Departments: " + departmentRepository.count());
        System.out.println("  • Total Students: " + studentRepository.count());
        System.out.println("  • Total Courses: " + courseRepository.count());
    }
}