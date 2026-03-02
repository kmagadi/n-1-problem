package com.example.SpringTraining.dto;

public class StudentDTO {
    private String studentName;
    private int coursesCount;

    public StudentDTO() {
    }

    public StudentDTO(String studentName, int coursesCount) {
        this.studentName = studentName;
        this.coursesCount = coursesCount;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getCoursesCount() {
        return coursesCount;
    }

    public void setCoursesCount(int coursesCount) {
        this.coursesCount = coursesCount;
    }
}
