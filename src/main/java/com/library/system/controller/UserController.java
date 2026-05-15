package com.library.system.controller;

import com.library.system.model.AdminUser;
import com.library.system.model.RegularUser;
import com.library.system.model.User;
import com.library.system.service.UserService;
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
                           @RequestParam(required = false) String membership) throws IOException {
        
        String id = UUID.randomUUID().toString().substring(0, 8);
        User user;
        if (type.equals("ADMIN")) {
            user = new AdminUser(id, username, password, email);
        } else {
            user = new RegularUser(id, username, password, email, membership);
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
