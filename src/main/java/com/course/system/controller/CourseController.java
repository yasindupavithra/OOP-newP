package com.course.system.controller;

import com.course.system.model.*;
import com.course.system.service.CourseService;
import com.course.system.service.RegistrationService;
import com.course.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired private CourseService courseService;
    @Autowired private UserService userService;
    @Autowired private RegistrationService registrationService;

    @GetMapping
    public String listCourses(@RequestParam(required = false) String search, Model model) throws IOException {
        List<Course> courses = courseService.getAllCourses();

        if (search != null && !search.isBlank()) {
            String q = search.toLowerCase();
            courses = courses.stream()
                    .filter(c -> c.getTitle().toLowerCase().contains(q)
                            || c.getCourseCode().toLowerCase().contains(q))
                    .collect(Collectors.toList());
        }

        // NEW: attach enrollment counts for capacity display
        for (Course c : courses) {
            int enrolled = registrationService.countEnrolledForCourse(c.getId());
            model.addAttribute("enrolled_" + c.getId(), enrolled);
        }

        model.addAttribute("courses", courses);
        model.addAttribute("search", search);
        return "courses/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) throws IOException {
        // NEW: pass instructors list so admin can pick one
        model.addAttribute("instructors", userService.getAllInstructors());
        return "courses/add";
    }

    @PostMapping("/add")
    public String addCourse(@RequestParam String title,
                            @RequestParam String instructorId,
                            @RequestParam String instructorName,
                            @RequestParam int credits,
                            @RequestParam String code,
                            @RequestParam String type,
                            @RequestParam(defaultValue = "30") int maxCapacity,
                            @RequestParam(required = false) String room,
                            @RequestParam(required = false) String location,
                            @RequestParam(required = false) String platform,
                            @RequestParam(required = false) String link,
                            RedirectAttributes ra) throws IOException {

        // IMPROVEMENT: validation
        if (title == null || title.isBlank() || code == null || code.isBlank()) {
            ra.addFlashAttribute("error", "Title and course code are required.");
            return "redirect:/courses/add";
        }

        String id = UUID.randomUUID().toString().substring(0, 8);
        Course course;
        // IMPROVEMENT: use CourseType enum
        if (CourseType.ONLINE.name().equals(type)) {
            course = new OnlineCourse(id, title.trim(), instructorId, instructorName,
                    credits, code.trim().toUpperCase(), true, maxCapacity,
                    platform != null ? platform : "", link != null ? link : "");
        } else {
            course = new OnsiteCourse(id, title.trim(), instructorId, instructorName,
                    credits, code.trim().toUpperCase(), true, maxCapacity,
                    room != null ? room : "", location != null ? location : "");
        }

        courseService.addCourse(course);
        ra.addFlashAttribute("success", "Course added successfully.");
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable String id, RedirectAttributes ra) throws IOException {
        courseService.deleteCourse(id);
        ra.addFlashAttribute("success", "Course deleted.");
        return "redirect:/courses";
    }
}
