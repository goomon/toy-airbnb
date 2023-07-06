package com.clone.airbnb.domain.room.controller;

import com.clone.airbnb.domain.room.form.RoomForm;
import com.clone.airbnb.domain.room.model.Room;
import com.clone.airbnb.domain.room.service.RoomService;
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
public class RoomAdminController {

    private final RoomService roomService;
    private final UserService userService;

    @GetMapping("/admin/room")
    public String list(Model model) {
        List<Room> rooms = roomService.findAll();
        model.addAttribute("rooms", rooms);
        return "room/list";
    }

    @GetMapping("/admin/room/new")
    public String form(Model model) {
        List<User> hosts = userService.findHost();
        model.addAttribute("hosts", hosts);
        model.addAttribute("roomForm", new RoomForm());
        return "room/form";
    }

    @PostMapping("/admin/room/new")
    public String create(@Valid RoomForm roomForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<User> hosts = userService.findHost();
            model.addAttribute("hosts", hosts);
            return "room/form";
        }
        roomService.save(roomForm);
        return "redirect:/admin/room";
    }

    @GetMapping("/admin/room/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        List<User> hosts = userService.findHost();
        Room room = roomService.findById(id);
        model.addAttribute("hosts", hosts);
        model.addAttribute("roomForm", new RoomForm(room));
        return "room/update";
    }

    @PostMapping("/admin/room/{id}/edit")
    public String update(@PathVariable("id") Long id, @ModelAttribute("roomForm") RoomForm roomForm) {
        roomService.update(id, roomForm);
        return "redirect:/admin/room";
    }
}
