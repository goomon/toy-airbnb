package com.clone.airbnb.domain.contents.controller;

import com.clone.airbnb.domain.contents.form.ExperienceForm;
import com.clone.airbnb.domain.contents.model.Experience;
import com.clone.airbnb.domain.contents.service.ExperienceService;
import com.clone.airbnb.domain.users.model.User;
import com.clone.airbnb.domain.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
public class ExperienceAdminController {

    private final ExperienceService experienceService;
    private final UserService userService;

    @GetMapping("/admin/experience")
    public String list(Model model) {
        List<Experience> experiences = experienceService.findAll();
        model.addAttribute("experiences", experiences);
        return "experience/list";
    }

    @GetMapping("/admin/experience/new")
    public String form(Model model) {
        List<User> hosts = userService.findHost();
        model.addAttribute("hosts", hosts);
        model.addAttribute("experienceForm", new ExperienceForm());
        return "experience/form";
    }

    @PostMapping("/admin/experience/new")
    public String create(@Valid ExperienceForm experienceForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<User> hosts = userService.findHost();
            model.addAttribute("hosts", hosts);
            return "experience/form";
        }
        experienceService.save(experienceForm);
        return "redirect:/admin/experience";
    }

    @GetMapping("/admin/experience/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        List<User> hosts = userService.findHost();
        Experience experience = experienceService.findById(id);
        model.addAttribute("experienceForm", new ExperienceForm(experience));
        model.addAttribute("hosts", hosts);
        return "experience/update";
    }

    @PostMapping("/admin/experience/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("experienceForm") ExperienceForm experienceForm) {
        experienceService.update(id, experienceForm);
        return "redirect:/admin/experience";
    }
}
