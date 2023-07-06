package com.clone.airbnb.domain.contents.controller;

import com.clone.airbnb.domain.contents.dto.PerkDto;
import com.clone.airbnb.domain.contents.model.Perk;
import com.clone.airbnb.domain.contents.service.PerkService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/perk")
@RequiredArgsConstructor
public class PerkController {

    private final PerkService perkService;

    @GetMapping
    public List<Perk> findAll() {
        return perkService.findAll();
    }

    @GetMapping("/{id}")
    public Perk findById(@PathVariable("id") Long id) {
        return perkService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody PerkDto requestData) {
        Perk perk = new Perk(requestData);
        perkService.save(perk);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        perkService.deleteById(id);
    }
}
