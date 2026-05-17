package com.course.system.service;

import com.course.system.model.*;
import com.course.system.model.exception.CourseFullException;
import com.course.system.model.exception.DuplicateRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class RegistrationService {

    private static final String FILE_NAME = "registrations.txt";

    @Autowired
    private FileService fileService;

    @Autowired
    private CourseService courseService;

    /**
     * IMPROVEMENT: Validates before saving:
     * 1. Checks for duplicate enrollment
     * 2. Checks course capacity
     * Uses Strategy pattern (via Registration.applyFeeStrategy) for fee
     */
    public void addRegistration(Registration registration, String studentType,
                                int currentEnrollmentCount) throws IOException {
        // IMPROVEMENT: duplicate check
        boolean duplicate = getAllRegistrations().stream()
                .anyMatch(r -> r.getStudentId().equals(registration.getStudentId())
                        && r.getCourseId().equals(registration.getCourseId())
                        && r.getStatus() == RegistrationStatus.ENROLLED);
        if (duplicate) {
            throw new DuplicateRegistrationException(registration.getStudentId(), registration.getCourseId());
        }

        // NEW: capacity check
        Course course = courseService.getCourseById(registration.getCourseId()).orElse(null);
        if (course != null && currentEnrollmentCount >= course.getMaxCapacity()) {
            throw new CourseFullException(course.getTitle());
        }

        // IMPROVEMENT (Strategy Pattern): apply fee via strategy
        registration.applyFeeStrategy(studentType);
        fileService.appendToFile(FILE_NAME, registration.toString());
    }

    public List<Registration> getAllRegistrations() throws IOException {
        List<String> lines = fileService.readFromFile(FILE_NAME);
        List<Registration> list = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) continue;
            String[] p = line.split("\\|");
            // Format: id|studentId|courseId|studentName|courseTitle|date|fee|status|studentType
            if (p.length >= 8) {
                Registration r = new Registration();
                r.setId(p[0]);
                r.setStudentId(p[1]);
                r.setCourseId(p[2]);
                r.setStudentName(p.length > 3 ? p[3] : "");
                r.setCourseTitle(p.length > 4 ? p[4] : "");
                r.setRegistrationDate(LocalDate.parse(p[5]));
                r.setRegistrationFee(Double.parseDouble(p[6]));
                // IMPROVEMENT: parse RegistrationStatus enum
                try {
                    r.setStatus(RegistrationStatus.valueOf(p[7].trim()));
                } catch (IllegalArgumentException e) {
                    r.setStatus(RegistrationStatus.ENROLLED);
                }
                r.setStudentType(p.length > 8 ? p[8] : "REGULAR");
                list.add(r);
            }
        }
        return list;
    }

    /** NEW: Drop a course (sets status to DROPPED, keeps history) */
    public void dropRegistration(String registrationId) throws IOException {
        List<Registration> list = getAllRegistrations();
        List<String> updatedLines = new ArrayList<>();
        for (Registration r : list) {
            if (r.getId().equals(registrationId)) {
                r.setStatus(RegistrationStatus.DROPPED);
            }
            updatedLines.add(r.toString());
        }
        fileService.writeToFile(FILE_NAME, updatedLines);
    }

    /** Returns count of ENROLLED students for a course (for capacity check) */
    public int countEnrolledForCourse(String courseId) throws IOException {
        return (int) getAllRegistrations().stream()
                .filter(r -> r.getCourseId().equals(courseId)
                        && r.getStatus() == RegistrationStatus.ENROLLED)
                .count();
    }

    public void updateRegistration(Registration updated) throws IOException {
        List<Registration> list = getAllRegistrations();
        List<String> updatedLines = new ArrayList<>();
        for (Registration r : list) {
            updatedLines.add(r.getId().equals(updated.getId()) ? updated.toString() : r.toString());
        }
        fileService.writeToFile(FILE_NAME, updatedLines);
    }
}
