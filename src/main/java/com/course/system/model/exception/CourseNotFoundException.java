package com.course.system.model.exception;

/** IMPROVEMENT: Custom exception instead of silent null returns */
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String courseId) {
        super("Course not found with ID: " + courseId);
    }
}
