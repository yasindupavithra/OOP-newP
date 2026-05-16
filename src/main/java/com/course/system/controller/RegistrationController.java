package com.course.system.controller;

import com.course.system.model.Course;
import com.course.system.model.Registration;
import com.course.system.model.Student;
import com.course.system.model.User;
import com.course.system.service.CourseService;
import com.course.system.service.RegistrationService;
import com.course.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String listRegistrations(Model model) throws IOException {
        model.addAttribute("registrations", registrationService.getAllRegistrations());
        return "registrations/list";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) throws IOException {
        List<Course> openCourses = courseService.getAllCourses().stream()
                .filter(Course::isOpenForRegistration)
                .collect(Collectors.toList());
        List<User> students = userService.getAllUsers().stream()
                .filter(u -> u.getUserType().equals("STUDENT"))
                .collect(Collectors.toList());
        
        model.addAttribute("courses", openCourses);
        model.addAttribute("students", students);
        return "registrations/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String studentId, @RequestParam String courseId,
                          @RequestParam String studentType) throws IOException {
        
        String id = UUID.randomUUID().toString().substring(0, 8);
        Registration r = new Registration(id, studentId, courseId, LocalDate.now(), "ENROLLED");
        r.calculateFee(studentType);
        
        registrationService.addRegistration(r);
        return "redirect:/registrations";
    }
}
