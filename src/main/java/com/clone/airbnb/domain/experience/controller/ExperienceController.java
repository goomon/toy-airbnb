package com.clone.airbnb.domain.experience.controller;

import com.clone.airbnb.domain.experience.dto.ExperienceDto;
import com.clone.airbnb.domain.experience.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping
    public List<ExperienceDto> findAll() {
        return experienceService.findAll().stream()
                .map(ExperienceDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ExperienceDto findById(@PathVariable("id") Long id) {
        return new ExperienceDto(experienceService.findById(id));
    }

    @PostMapping
    public void save(@RequestBody ExperienceDto requestData) {
        experienceService.save(requestData);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        experienceService.deleteById(id);
    }
}
