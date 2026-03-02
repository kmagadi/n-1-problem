package com.example.SpringTraining.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.SpringTraining.entites.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
