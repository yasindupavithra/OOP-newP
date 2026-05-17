package com.course.system.model;

import com.course.system.model.strategy.FeeStrategy;
import com.course.system.model.strategy.InternationalFeeStrategy;
import com.course.system.model.strategy.LocalFeeStrategy;

import java.time.LocalDate;
import java.util.Objects;

/**
 * IMPROVEMENT (OOP - Strategy Pattern):
 * Fee calculation is now delegated to a FeeStrategy object.
 * Adding a new student type = add a new FeeStrategy class, no changes here.
 *
 * IMPROVEMENT: RegistrationStatus enum instead of raw "ENROLLED"/"DROPPED" strings.
 * IMPROVEMENT: equals() and hashCode() added.
 * NEW: studentName and courseTitle stored for display in views (fixes raw ID display bug).
 */
public class Registration {
    private static final double BASE_FEE = 5000.0;

    private String id;
    private String studentId;
    private String courseId;
    private String studentName;   // NEW: for display
    private String courseTitle;   // NEW: for display
    private LocalDate registrationDate;
    private double registrationFee;
    private RegistrationStatus status;
    private String studentType;   // store for display

    public Registration() {}

    public Registration(String id, String studentId, String courseId,
                        String studentName, String courseTitle,
                        LocalDate registrationDate, RegistrationStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseTitle = courseTitle;
        this.registrationDate = registrationDate;
        this.status = status;
        this.registrationFee = 0.0;
    }

    // IMPROVEMENT (Strategy Pattern): fee calculation delegated to strategy
    public void applyFeeStrategy(String studentType) {
        this.studentType = studentType;
        FeeStrategy strategy;
        if ("INTERNATIONAL".equalsIgnoreCase(studentType)) {
            strategy = new InternationalFeeStrategy();
        } else {
            strategy = new LocalFeeStrategy();
        }
        this.registrationFee = strategy.calculateFee(BASE_FEE);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public double getRegistrationFee() { return registrationFee; }
    public void setRegistrationFee(double registrationFee) { this.registrationFee = registrationFee; }

    public RegistrationStatus getStatus() { return status; }
    public void setStatus(RegistrationStatus status) { this.status = status; }

    public String getStudentType() { return studentType != null ? studentType : "REGULAR"; }
    public void setStudentType(String studentType) { this.studentType = studentType; }

    // IMPROVEMENT: equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Registration)) return false;
        Registration r = (Registration) o;
        return Objects.equals(id, r.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + "|" + studentId + "|" + courseId + "|"
                + (studentName != null ? studentName : "") + "|"
                + (courseTitle != null ? courseTitle : "") + "|"
                + registrationDate + "|" + registrationFee + "|"
                + status.name() + "|" + getStudentType();
    }
}
