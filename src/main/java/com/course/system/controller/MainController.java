package com.course.system.controller;

import com.course.system.service.CourseService;
import com.course.system.service.RegistrationService;
import com.course.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/")
    public String index(Model model) throws IOException {
        // Advanced Statistics for Member 01 (The User)
        long totalStudents = userService.getAllUsers().stream()
                .filter(u -> u.getUserType().equals("STUDENT")).count();
        long totalCourses = courseService.getAllCourses().size();
        long totalRegistrations = registrationService.getAllRegistrations().size();
        double totalFees = registrationService.getAllRegistrations().stream()
                .mapToDouble(r -> r.getRegistrationFee()).sum();

        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("totalCourses", totalCourses);
        model.addAttribute("totalRegistrations", totalRegistrations);
        model.addAttribute("totalFees", totalFees);

        return "index";
    }
}
