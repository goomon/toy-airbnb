package com.clone.airbnb.domain.users.controller;

import com.clone.airbnb.domain.users.form.UserForm;
import com.clone.airbnb.domain.users.model.User;
import com.clone.airbnb.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserAdminController {

    private final UserService userService;

    @GetMapping("/admin/users")
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/admin/users/new")
    public String form(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "users/form";
    }

    @PostMapping("/admin/users/new")
    public String create(UserForm userForm) {
        User user = new User(userForm);
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("form", new UserForm(user));
        return "users/update";
    }

    @PostMapping("/admin/users/{id}/edit")
    public String update(@PathVariable("id") Long id, @ModelAttribute("form") UserForm userForm) {
        userService.update(id, userForm);
        log.info(userForm.getName());
        return "redirect:/admin/users";
    }
}
