package com.example.SpringTraining.controller;

import com.example.SpringTraining.dto.DepartmentDTO;
import com.example.SpringTraining.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DepartmentController
 *
 * REST API Controller for Department operations
 * Provides endpoints for retrieving department and student information
 */
@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * GET /api/departments
     *
     * Retrieves all departments with their students and course counts
     * Solves N+1 query problem using LEFT JOIN FETCH in repository
     *
     * Response format:
     * [
     *   {
     *     "departmentName": "Computer Science",
     *     "students": [
     *       {
     *         "studentName": "Rahul",
     *         "coursesCount": 5
     *       }
     *     ]
     *   }
     * ]
     *
     * @return ResponseEntity with List of DepartmentDTO and HTTP status 200 OK
     */
    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDTO>> getDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}

