package com.course.system.model;

import java.util.Objects;

/**
 * NEW COMPONENT: Grade model.
 * Tracks the grade assigned to a student's course registration.
 * Demonstrates domain model extension beyond just enrollment.
 */
public class Grade {
    private String id;
    private String registrationId;
    private String studentId;
    private String courseId;
    private String studentName;
    private String courseTitle;
    private double gradeValue;    // 0.0 - 100.0
    private String letterGrade;  // A, B, C, D, F

    public Grade() {}

    public Grade(String id, String registrationId, String studentId, String courseId,
                 String studentName, String courseTitle, double gradeValue) {
        this.id = id;
        this.registrationId = registrationId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseTitle = courseTitle;
        this.gradeValue = gradeValue;
        this.letterGrade = computeLetterGrade(gradeValue);
    }

    /** Computes letter grade from numeric score */
    private String computeLetterGrade(double value) {
        if (value >= 90) return "A";
        if (value >= 80) return "B";
        if (value >= 70) return "C";
        if (value >= 60) return "D";
        return "F";
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRegistrationId() { return registrationId; }
    public void setRegistrationId(String registrationId) { this.registrationId = registrationId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    public double getGradeValue() { return gradeValue; }
    public void setGradeValue(double gradeValue) {
        this.gradeValue = gradeValue;
        this.letterGrade = computeLetterGrade(gradeValue);
    }

    public String getLetterGrade() { return letterGrade; }
    public void setLetterGrade(String letterGrade) { this.letterGrade = letterGrade; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade)) return false;
        return Objects.equals(id, ((Grade) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return id + "|" + registrationId + "|" + studentId + "|" + courseId + "|"
                + studentName + "|" + courseTitle + "|" + gradeValue + "|" + letterGrade;
    }
}
