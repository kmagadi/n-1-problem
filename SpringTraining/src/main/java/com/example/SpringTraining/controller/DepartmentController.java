package com.example.SpringTraining.controller;

import com.example.SpringTraining.config.QueryCounter;
import com.example.SpringTraining.dto.DepartmentDTO;
import com.example.SpringTraining.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * DepartmentController
 *
 * REST API Controller for Department operations
 * Provides endpoints for retrieving department and student information
 */
@RestController
@RequestMapping("/api")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final QueryCounter queryCounter;

    public DepartmentController(DepartmentService departmentService,
                                QueryCounter queryCounter) {
        this.departmentService = departmentService;
        this.queryCounter = queryCounter;
    }

    @GetMapping("/departments/join-fetch")
    public ResponseEntity<?> getDepartments() {

        queryCounter.reset();

        List<DepartmentDTO> result = departmentService.getAllDepartments();

        long queries = queryCounter.getQueryCount();

        return ResponseEntity.ok(
                Map.of(
                        "data", result,
                        "queryCount", queries
                )
        );
    }
}