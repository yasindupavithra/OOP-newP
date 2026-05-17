package com.course.system.model.exception;

/** IMPROVEMENT: Custom exception for course capacity exceeded */
public class CourseFullException extends RuntimeException {
    public CourseFullException(String courseTitle) {
        super("Course '" + courseTitle + "' has reached maximum capacity.");
    }
}
