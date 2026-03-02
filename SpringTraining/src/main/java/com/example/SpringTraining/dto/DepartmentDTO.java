package com.example.SpringTraining.dto;

import java.util.List;

public class DepartmentDTO {
    private String departmentName;
    private List<StudentDTO> students;

    public DepartmentDTO() {
    }

    public DepartmentDTO(String departmentName, List<StudentDTO> students) {
        this.departmentName = departmentName;
        this.students = students;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}

