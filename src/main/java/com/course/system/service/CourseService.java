package com.course.system.service;

import com.course.system.model.*;
import com.course.system.model.exception.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CourseService {

    private static final String FILE_NAME = "courses.txt";

    @Autowired
    private FileService fileService;

    public void addCourse(Course course) throws IOException {
        fileService.appendToFile(FILE_NAME, course.toString());
    }

    public List<Course> getAllCourses() throws IOException {
        List<String> lines = fileService.readFromFile(FILE_NAME);
        List<Course> courses = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) continue;
            String[] p = line.split("\\|");
            // Format: id|title|instructorId|instructorName|credits|courseCode|open|maxCapacity|type|[extra fields]
            if (p.length >= 9) {
                String id           = p[0];
                String title        = p[1];
                String instructorId = p[2];
                String instrName    = p[3];
                int credits         = Integer.parseInt(p[4]);
                String code         = p[5];
                boolean open        = Boolean.parseBoolean(p[6]);
                int maxCap          = Integer.parseInt(p[7]);
                String type         = p[8];

                // IMPROVEMENT: use CourseType enum
                if (CourseType.ONLINE.name().equals(type)) {
                    courses.add(new OnlineCourse(id, title, instructorId, instrName,
                            credits, code, open, maxCap,
                            p.length > 9 ? p[9] : "", p.length > 10 ? p[10] : ""));
                } else {
                    courses.add(new OnsiteCourse(id, title, instructorId, instrName,
                            credits, code, open, maxCap,
                            p.length > 9 ? p[9] : "", p.length > 10 ? p[10] : ""));
                }
            }
        }
        return courses;
    }

    public Optional<Course> getCourseById(String id) throws IOException {
        return getAllCourses().stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    /** IMPROVEMENT: throws custom exception instead of returning empty */
    public Course getCourseByIdOrThrow(String id) throws IOException {
        return getCourseById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    public void updateCourse(Course updated) throws IOException {
        List<Course> courses = getAllCourses();
        List<String> lines = new ArrayList<>();
        for (Course c : courses) {
            lines.add(c.getId().equals(updated.getId()) ? updated.toString() : c.toString());
        }
        fileService.writeToFile(FILE_NAME, lines);
    }

    public void deleteCourse(String id) throws IOException {
        List<Course> courses = getAllCourses();
        List<String> lines = new ArrayList<>();
        for (Course c : courses) {
            if (!c.getId().equals(id)) lines.add(c.toString());
        }
        fileService.writeToFile(FILE_NAME, lines);
    }
}
