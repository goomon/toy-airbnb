package com.clone.airbnb.domain.media.controller;

import com.clone.airbnb.domain.experience.model.Experience;
import com.clone.airbnb.domain.experience.service.ExperienceService;
import com.clone.airbnb.domain.media.form.PhotoForm;
import com.clone.airbnb.domain.media.model.Photo;
import com.clone.airbnb.domain.media.service.PhotoService;
import com.clone.airbnb.domain.media.store.FileStore;
import com.clone.airbnb.domain.room.model.Room;
import com.clone.airbnb.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/photo")
@RequiredArgsConstructor
public class PhotoAdminController {

    private final FileStore fileStore;
    private final PhotoService photoService;
    private final RoomService roomService;
    private final ExperienceService experienceService;

    @GetMapping
    public String list(Model model) {
        List<Photo> photos = photoService.findAll();
        model.addAttribute("photos", photos);
        return "media/photo/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        List<Room> rooms = roomService.findAll();
        List<Experience> experiences = experienceService.findAll();

        model.addAttribute("rooms", rooms);
        model.addAttribute("experiences", experiences);
        model.addAttribute("photoForm", new PhotoForm());
        return "media/photo/form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("photoForm") PhotoForm photoForm,
                         BindingResult bindingResult, Model model) throws IOException {
        MultipartFile file = photoForm.getFile();
        if (file.isEmpty()) {
            bindingResult.addError(new FieldError("photoForm", "file", "파일을 선택해야 합니다."));
        }
        if (photoForm.getRoomId() == null && photoForm.getExperienceId() == null) {
            bindingResult.addError(new FieldError("photoForm", "roomId",
                    "둘 중 하나의 항목은 반드시 선택해야 합니다."));
            bindingResult.addError(new FieldError("photoForm", "experienceId",
                    "둘 중 하나의 항목은 반드시 선택해야 합니다."));
        }
        if (photoForm.getRoomId() != null && photoForm.getExperienceId() != null) {
            bindingResult.addError(new FieldError("photoForm", "roomId",
                    "둘 장 하나의 항목만 선택해야 합니다."));
            bindingResult.addError(new FieldError("photoForm", "experienceId",
                    "둘 중 하나의 항목만 선택해야 합니다."));
        }

        if (bindingResult.hasErrors()) {
            List<Room> rooms = roomService.findAll();
            List<Experience> experiences = experienceService.findAll();
            model.addAttribute("rooms", rooms);
            model.addAttribute("experiences", experiences);
            return "media/photo/form";
        }

        fileStore.storeFile(file);
        photoService.saveForm(photoForm);
        return "redirect:/admin/photo";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable("filename") String filename) throws MalformedURLException {
        String fullPath = fileStore.getFullPath(filename);
        return new UrlResource("file:" + fullPath);
    }
}
