package com.course.system.controller;

import com.course.system.model.Course;
import com.course.system.model.OnlineCourse;
import com.course.system.model.OnsiteCourse;
import com.course.system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String listCourses(@RequestParam(required = false) String search, Model model) throws IOException {
        List<Course> courses = courseService.getAllCourses();
        
        // Advanced Filtering for Member 01
        if (search != null && !search.isEmpty()) {
            courses = courses.stream()
                    .filter(c -> c.getTitle().toLowerCase().contains(search.toLowerCase()) || 
                                 c.getCourseCode().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        model.addAttribute("courses", courses);
        model.addAttribute("search", search);
        return "courses/list";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "courses/add";
    }

    @PostMapping("/add")
    public String addCourse(@RequestParam String title, @RequestParam String instructor,
                           @RequestParam int credits, @RequestParam String code,
                           @RequestParam String type, @RequestParam(required = false) String room,
                           @RequestParam(required = false) String location,
                           @RequestParam(required = false) String platform,
                           @RequestParam(required = false) String link) throws IOException {
        
        String id = UUID.randomUUID().toString().substring(0, 8);
        Course course;
        if (type.equals("ONLINE")) {
            course = new OnlineCourse(id, title, instructor, credits, code, true, platform, link);
        } else {
            course = new OnsiteCourse(id, title, instructor, credits, code, true, room, location);
        }
        
        courseService.addCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable String id) throws IOException {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
