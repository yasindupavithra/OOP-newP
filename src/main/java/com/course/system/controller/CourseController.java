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
import java.util.UUID;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String listCourses(Model model) throws IOException {
        model.addAttribute("courses", courseService.getAllCourses());
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
