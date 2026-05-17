package com.course.system.service;

import com.course.system.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * NEW COMPONENT: GradeService handles storing and retrieving grades.
 * Grades are persisted to grades.txt in the same file-based storage pattern.
 */
@Service
public class GradeService {

    private static final String FILE_NAME = "grades.txt";

    @Autowired
    private FileService fileService;

    public void addGrade(Grade grade) throws IOException {
        fileService.appendToFile(FILE_NAME, grade.toString());
    }

    public List<Grade> getAllGrades() throws IOException {
        List<String> lines = fileService.readFromFile(FILE_NAME);
        List<Grade> grades = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) continue;
            String[] p = line.split("\\|");
            // Format: id|registrationId|studentId|courseId|studentName|courseTitle|gradeValue|letterGrade
            if (p.length >= 8) {
                Grade g = new Grade();
                g.setId(p[0]);
                g.setRegistrationId(p[1]);
                g.setStudentId(p[2]);
                g.setCourseId(p[3]);
                g.setStudentName(p[4]);
                g.setCourseTitle(p[5]);
                g.setGradeValue(Double.parseDouble(p[6]));
                g.setLetterGrade(p[7]);
                grades.add(g);
            }
        }
        return grades;
    }

    /** Get all grades for a specific student */
    public List<Grade> getGradesByStudent(String studentId) throws IOException {
        return getAllGrades().stream()
                .filter(g -> g.getStudentId().equals(studentId))
                .toList();
    }

    /** Check if a grade already exists for this registration */
    public boolean gradeExistsForRegistration(String registrationId) throws IOException {
        return getAllGrades().stream()
                .anyMatch(g -> g.getRegistrationId().equals(registrationId));
    }
}
