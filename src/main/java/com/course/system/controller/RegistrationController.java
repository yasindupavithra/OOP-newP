package com.course.system.controller;

import com.course.system.model.*;
import com.course.system.model.exception.CourseFullException;
import com.course.system.model.exception.DuplicateRegistrationException;
import com.course.system.service.CourseService;
import com.course.system.service.RegistrationService;
import com.course.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired private RegistrationService registrationService;
    @Autowired private CourseService courseService;
    @Autowired private UserService userService;

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
        List<User> students = userService.getAllStudents();

        model.addAttribute("courses", openCourses);
        model.addAttribute("students", students);
        return "registrations/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String studentId,
                           @RequestParam String courseId,
                           @RequestParam String studentType,
                           RedirectAttributes ra) throws IOException {

        // Resolve display names
        String studentName = userService.getUserById(studentId)
                .map(User::getUsername).orElse(studentId);
        String courseTitle = courseService.getCourseById(courseId)
                .map(Course::getTitle).orElse(courseId);

        String id = UUID.randomUUID().toString().substring(0, 8);
        Registration r = new Registration(id, studentId, courseId,
                studentName, courseTitle, LocalDate.now(), RegistrationStatus.ENROLLED);

        int currentEnrolled = registrationService.countEnrolledForCourse(courseId);

        try {
            // IMPROVEMENT: duplicate check + capacity check + strategy pattern all inside service
            registrationService.addRegistration(r, studentType, currentEnrolled);
            ra.addFlashAttribute("success", studentName + " enrolled in " + courseTitle + " successfully.");
        } catch (DuplicateRegistrationException e) {
            // IMPROVEMENT: custom exception handling
            ra.addFlashAttribute("error", e.getMessage());
        } catch (CourseFullException e) {
            // IMPROVEMENT: custom exception handling
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/registrations";
    }

    /** NEW: Drop a course registration */
    @GetMapping("/drop/{id}")
    public String dropRegistration(@PathVariable String id, RedirectAttributes ra) throws IOException {
        registrationService.dropRegistration(id);
        ra.addFlashAttribute("success", "Registration dropped successfully.");
        return "redirect:/registrations";
    }
}
