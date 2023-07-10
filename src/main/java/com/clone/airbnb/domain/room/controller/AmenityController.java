package com.clone.airbnb.domain.room.controller;

import com.clone.airbnb.domain.room.dto.AmenityDto;
import com.clone.airbnb.domain.room.model.Amenity;
import com.clone.airbnb.domain.room.service.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/amenity")
@RequiredArgsConstructor
public class AmenityController {

    private final AmenityService amenityService;

    @GetMapping
    public List<Amenity> findAll() {
        return amenityService.findAll();
    }

    @GetMapping("/{id}")
    public Amenity findById(@PathVariable("id") Long id) {
        return amenityService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody AmenityDto requestData) {
        Amenity amenity = new Amenity(requestData);
        amenityService.save(amenity);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        amenityService.deleteById(id);
    }
}
