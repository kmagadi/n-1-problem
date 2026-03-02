package com.example.SpringTraining.service;

import com.example.SpringTraining.config.HibernateQueryCounter;
import com.example.SpringTraining.dto.DepartmentDTO;
import com.example.SpringTraining.dto.QueryMetricsResponse;
import com.example.SpringTraining.dto.StudentDTO;
import com.example.SpringTraining.entites.Department;
import com.example.SpringTraining.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HibernateQueryCounter queryCounter;

    public DepartmentService(DepartmentRepository departmentRepository,
                             HibernateQueryCounter queryCounter) {
        this.departmentRepository = departmentRepository;
        this.queryCounter = queryCounter;
    }

    /*
     * FETCH JOIN (already implemented)
     */
    public QueryMetricsResponse<DepartmentDTO> getUsingFetchJoin() {

        queryCounter.reset();
        long start = System.currentTimeMillis();

        List<Department> departments =
                departmentRepository.findAllWithStudentsAndCourses();

        List<DepartmentDTO> result =
                departments.stream().map(this::convertToDTO).toList();

        long time = System.currentTimeMillis() - start;

        return new QueryMetricsResponse<>(
                result,
                queryCounter.getQueryCount(),
                time
        );
    }

    /*
     * ENTITY GRAPH
     */
    public QueryMetricsResponse<DepartmentDTO> getUsingEntityGraph() {

        queryCounter.reset();
        long start = System.currentTimeMillis();

        List<Department> departments = departmentRepository.findAll();

        List<DepartmentDTO> result =
                departments.stream().map(this::convertToDTO).toList();

        long time = System.currentTimeMillis() - start;

        return new QueryMetricsResponse<>(
                result,
                queryCounter.getQueryCount(),
                time
        );
    }

    /*
     * Common DTO conversion
     */
    private DepartmentDTO convertToDTO(Department department) {

        List<StudentDTO> studentDTOs = department.getStudents().stream()
                .map(student -> new StudentDTO(
                        student.getStudentName(),
                        student.getCourses() != null
                                ? student.getCourses().size()
                                : 0
                ))
                .toList();

        return new DepartmentDTO(
                department.getDepartmentName(),
                studentDTOs
        );
    }
}