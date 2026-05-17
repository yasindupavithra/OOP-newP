package com.course.system.model.exception;

/** IMPROVEMENT: Custom exception for duplicate enrollment attempts */
public class DuplicateRegistrationException extends RuntimeException {
    public DuplicateRegistrationException(String studentId, String courseId) {
        super("Student " + studentId + " is already enrolled in course " + courseId);
    }
}
