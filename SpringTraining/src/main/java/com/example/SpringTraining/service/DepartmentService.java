package com.example.SpringTraining.service;

import com.example.SpringTraining.dto.DepartmentDTO;
import com.example.SpringTraining.dto.StudentDTO;
import com.example.SpringTraining.entites.Department;
import com.example.SpringTraining.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Get all departments with their students and course counts
     * Solves the N+1 query problem using LEFT JOIN FETCH
     *
     * @return List of DepartmentDTO objects
     */
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAllWithStudentsAndCourses();

        return departments.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Convert Department entity to DepartmentDTO
     * Calculates course count for each student
     *
     * @param department Department entity
     * @return DepartmentDTO with student information
     */
    private DepartmentDTO convertToDTO(Department department) {
        List<StudentDTO> studentDTOs = department.getStudents().stream()
            .map(student -> new StudentDTO(
                student.getStudentName(),
                student.getCourses() != null ? student.getCourses().size() : 0
            ))
            .collect(Collectors.toList());

        return new DepartmentDTO(department.getDepartmentName(), studentDTOs);
    }
}
