package com.course.system.controller;

import com.course.system.model.RegistrationStatus;
import com.course.system.service.CourseService;
import com.course.system.service.GradeService;
import com.course.system.service.RegistrationService;
import com.course.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MainController {

    @Autowired private UserService userService;
    @Autowired private CourseService courseService;
    @Autowired private RegistrationService registrationService;
    @Autowired private GradeService gradeService;

    @GetMapping("/")
    public String index(Model model) throws IOException {
        long totalStudents = userService.getAllStudents().size();
        long totalCourses = courseService.getAllCourses().size();
        // IMPROVEMENT: only count ENROLLED (not DROPPED)
        long totalRegistrations = registrationService.getAllRegistrations().stream()
                .filter(r -> r.getStatus() == RegistrationStatus.ENROLLED).count();
        double totalFees = registrationService.getAllRegistrations().stream()
                .filter(r -> r.getStatus() == RegistrationStatus.ENROLLED)
                .mapToDouble(r -> r.getRegistrationFee()).sum();
        long totalGrades = gradeService.getAllGrades().size();

        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("totalCourses", totalCourses);
        model.addAttribute("totalRegistrations", totalRegistrations);
        model.addAttribute("totalFees", String.format("%.2f", totalFees));
        model.addAttribute("totalGrades", totalGrades);
        return "index";
    }
}
