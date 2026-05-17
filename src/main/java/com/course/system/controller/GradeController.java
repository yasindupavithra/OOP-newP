package com.course.system.controller;

import com.course.system.model.Grade;
import com.course.system.model.Registration;
import com.course.system.model.RegistrationStatus;
import com.course.system.service.GradeService;
import com.course.system.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * NEW COMPONENT: GradeController manages grade entry and display.
 */
@Controller
@RequestMapping("/grades")
public class GradeController {

    @Autowired private GradeService gradeService;
    @Autowired private RegistrationService registrationService;

    @GetMapping
    public String listGrades(Model model) throws IOException {
        model.addAttribute("grades", gradeService.getAllGrades());
        return "grades/list";
    }

    @GetMapping("/assign")
    public String showAssignForm(Model model) throws IOException {
        // Only show ENROLLED registrations that don't yet have a grade
        List<Registration> enrolled = registrationService.getAllRegistrations().stream()
                .filter(r -> r.getStatus() == RegistrationStatus.ENROLLED)
                .collect(Collectors.toList());

        // Filter out those already graded
        List<Registration> ungradedRegistrations = enrolled.stream()
                .filter(r -> {
                    try { return !gradeService.gradeExistsForRegistration(r.getId()); }
                    catch (IOException e) { return true; }
                })
                .collect(Collectors.toList());

        model.addAttribute("registrations", ungradedRegistrations);
        return "grades/assign";
    }

    @PostMapping("/assign")
    public String assignGrade(@RequestParam String registrationId,
                              @RequestParam double gradeValue,
                              RedirectAttributes ra) throws IOException {

        Registration reg = registrationService.getAllRegistrations().stream()
                .filter(r -> r.getId().equals(registrationId))
                .findFirst().orElse(null);

        if (reg == null) {
            ra.addFlashAttribute("error", "Registration not found.");
            return "redirect:/grades/assign";
        }

        // IMPROVEMENT: validation
        if (gradeValue < 0 || gradeValue > 100) {
            ra.addFlashAttribute("error", "Grade must be between 0 and 100.");
            return "redirect:/grades/assign";
        }

        String id = UUID.randomUUID().toString().substring(0, 8);
        Grade grade = new Grade(id, reg.getId(), reg.getStudentId(), reg.getCourseId(),
                reg.getStudentName(), reg.getCourseTitle(), gradeValue);
        gradeService.addGrade(grade);

        ra.addFlashAttribute("success", "Grade assigned to " + reg.getStudentName() + " successfully.");
        return "redirect:/grades";
    }
}
