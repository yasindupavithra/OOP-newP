package com.course.system.controller;

import com.course.system.model.*;
import com.course.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) throws IOException {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email,
                           @RequestParam String type,
                           @RequestParam(required = false) String program,
                           @RequestParam(required = false) String department,
                           RedirectAttributes ra) throws IOException {

        // IMPROVEMENT: input validation
        if (username == null || username.isBlank() || email == null || email.isBlank()) {
            ra.addFlashAttribute("error", "Username and email are required.");
            return "redirect:/users/register";
        }

        String id = UUID.randomUUID().toString().substring(0, 8);
        User user;

        // IMPROVEMENT: use UserType enum
        if (UserType.ADMIN.name().equals(type)) {
            user = new Admin(id, username.trim(), password, email.trim());
        } else if (UserType.INSTRUCTOR.name().equals(type)) {
            // NEW: create Instructor
            user = new Instructor(id, username.trim(), password, email.trim(),
                    department != null ? department.trim() : "General");
        } else {
            user = new Student(id, username.trim(), password, email.trim(),
                    program != null ? program.trim() : "General");
        }

        userService.registerUser(user);
        ra.addFlashAttribute("success", "User registered successfully.");
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id, RedirectAttributes ra) throws IOException {
        userService.deleteUser(id);
        ra.addFlashAttribute("success", "User deleted.");
        return "redirect:/users";
    }
}
