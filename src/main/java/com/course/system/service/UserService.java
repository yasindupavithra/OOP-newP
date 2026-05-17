package com.course.system.service;

import com.course.system.model.*;
import com.course.system.model.exception.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class UserService {

    private static final String FILE_NAME = "users.txt";

    @Autowired
    private FileService fileService;

    public void registerUser(User user) throws IOException {
        fileService.appendToFile(FILE_NAME, user.toString());
    }

    public List<User> getAllUsers() throws IOException {
        List<String> lines = fileService.readFromFile(FILE_NAME);
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 5) {
                // IMPROVEMENT: use UserType enum for dispatch
                String typeStr = parts[4].trim();
                if (UserType.ADMIN.name().equals(typeStr)) {
                    users.add(new Admin(parts[0], parts[1], parts[2], parts[3]));
                } else if (UserType.INSTRUCTOR.name().equals(typeStr)) {
                    // NEW: parse Instructor
                    users.add(new Instructor(parts[0], parts[1], parts[2], parts[3],
                            parts.length > 5 ? parts[5] : "General"));
                } else {
                    // STUDENT
                    users.add(new Student(parts[0], parts[1], parts[2], parts[3],
                            parts.length > 5 ? parts[5] : "General"));
                }
            }
        }
        return users;
    }

    public Optional<User> getUserById(String id) throws IOException {
        return getAllUsers().stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    /** Returns only students */
    public List<User> getAllStudents() throws IOException {
        return getAllUsers().stream()
                .filter(u -> UserType.STUDENT.name().equals(u.getUserType()))
                .toList();
    }

    /** NEW: Returns only instructors */
    public List<User> getAllInstructors() throws IOException {
        return getAllUsers().stream()
                .filter(u -> UserType.INSTRUCTOR.name().equals(u.getUserType()))
                .toList();
    }

    public void deleteUser(String id) throws IOException {
        List<User> users = getAllUsers();
        List<String> updatedLines = new ArrayList<>();
        for (User u : users) {
            if (!u.getId().equals(id)) updatedLines.add(u.toString());
        }
        fileService.writeToFile(FILE_NAME, updatedLines);
    }
}
