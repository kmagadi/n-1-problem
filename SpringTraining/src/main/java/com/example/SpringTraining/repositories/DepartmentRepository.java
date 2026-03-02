package com.example.SpringTraining.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.SpringTraining.entites.Department;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT DISTINCT d FROM Department d LEFT JOIN FETCH d.students s LEFT JOIN FETCH s.courses")
    List<Department> findAllWithStudentsAndCourses();

    @EntityGraph(attributePaths = {"students", "students.courses"})
    @Query("SELECT d FROM Department d")
    List<Department> findAll();

}
