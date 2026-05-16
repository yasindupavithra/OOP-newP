package com.course.system.controller;

import com.course.system.model.Admin;
import com.course.system.model.Student;
import com.course.system.model.User;
import com.course.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String register(@RequestParam String username, @RequestParam String password,
                           @RequestParam String email, @RequestParam String type,
                           @RequestParam(required = false) String program) throws IOException {
        
        String id = UUID.randomUUID().toString().substring(0, 8);
        User user;
        if (type.equals("ADMIN")) {
            user = new Admin(id, username, password, email);
        } else {
            user = new Student(id, username, password, email, program);
        }
        
        userService.registerUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) throws IOException {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
