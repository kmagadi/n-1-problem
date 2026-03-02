package com.example.SpringTraining.service;

import com.example.SpringTraining.dto.DepartmentDTO;
import com.example.SpringTraining.dto.StudentDTO;
import com.example.SpringTraining.entites.Department;
import com.example.SpringTraining.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAllWithStudentsAndCourses();

        return departments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

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