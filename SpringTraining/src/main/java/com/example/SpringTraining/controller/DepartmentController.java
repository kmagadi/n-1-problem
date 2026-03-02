package com.example.SpringTraining.controller;

import com.example.SpringTraining.dto.DepartmentDTO;
import com.example.SpringTraining.dto.QueryMetricsResponse;
import com.example.SpringTraining.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/fetch-join")
    public ResponseEntity<QueryMetricsResponse<DepartmentDTO>> fetchJoin() {
        return ResponseEntity.ok(departmentService.getUsingFetchJoin());
    }

    @GetMapping("/entity-graph")
    public ResponseEntity<QueryMetricsResponse<DepartmentDTO>> entityGraph() {
        return ResponseEntity.ok(departmentService.getUsingEntityGraph());
    }
}