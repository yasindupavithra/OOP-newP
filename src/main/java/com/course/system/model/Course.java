package com.course.system.model;

import java.util.Objects;

/**
 * IMPROVEMENT (OOP):
 * - Now implements Registerable interface
 * - Added maxCapacity field for seat management
 * - Added equals() and hashCode()
 * - courseCode is now stored, type comes from subclass via getCourseType()
 */
public abstract class Course implements Registerable {
    private String id;
    private String title;
    private String instructorId;   // IMPROVEMENT: reference to Instructor ID, not plain string
    private String instructorName; // display name for convenience
    private int credits;
    private String courseCode;
    private boolean openForRegistration;
    private int maxCapacity;       // NEW: seat capacity

    public Course() {}

    public Course(String id, String title, String instructorId, String instructorName,
                  int credits, String courseCode, boolean openForRegistration, int maxCapacity) {
        this.id = id;
        this.title = title;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.credits = credits;
        this.courseCode = courseCode;
        this.openForRegistration = openForRegistration;
        this.maxCapacity = maxCapacity;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getInstructorId() { return instructorId; }
    public void setInstructorId(String instructorId) { this.instructorId = instructorId; }

    public String getInstructorName() { return instructorName; }
    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }

    // Keep getInstructor() for JSP backward-compat — returns display name
    public String getInstructor() { return instructorName; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    @Override
    public boolean isOpenForRegistration() { return openForRegistration; }
    public void setOpenForRegistration(boolean openForRegistration) { this.openForRegistration = openForRegistration; }

    @Override
    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    @Override
    public abstract String getCourseType();

    // IMPROVEMENT: equals and hashCode based on ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + "|" + title + "|" + instructorId + "|" + instructorName + "|"
                + credits + "|" + courseCode + "|" + openForRegistration + "|"
                + maxCapacity + "|" + getCourseType();
    }
}
