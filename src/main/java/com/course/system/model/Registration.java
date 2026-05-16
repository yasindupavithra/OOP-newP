package com.course.system.model;

import java.time.LocalDate;

public class Registration {
    private String id;
    private String studentId;
    private String courseId;
    private LocalDate registrationDate;
    private double registrationFee;
    private String status;

    public Registration() {}

    public Registration(String id, String studentId, String courseId, LocalDate registrationDate, String status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.registrationDate = registrationDate;
        this.status = status;
        this.registrationFee = 0.0;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public double getRegistrationFee() { return registrationFee; }
    public void setRegistrationFee(double registrationFee) { this.registrationFee = registrationFee; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Polymorphism: Fee calculation based on student type
    public void calculateFee(String studentType) {
        double baseFee = 5000.0;
        if (studentType.equalsIgnoreCase("INTERNATIONAL")) {
            this.registrationFee = baseFee * 2.5;
        } else {
            this.registrationFee = baseFee;
        }
    }

    @Override
    public String toString() {
        return id + "|" + studentId + "|" + courseId + "|" + registrationDate + "|" + registrationFee + "|" + status;
    }
}
