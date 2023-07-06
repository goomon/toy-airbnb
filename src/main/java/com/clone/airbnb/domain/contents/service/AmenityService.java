package com.clone.airbnb.domain.contents.service;

import com.clone.airbnb.domain.contents.model.Amenity;
import com.clone.airbnb.domain.contents.repository.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AmenityService {

    private final AmenityRepository amenityRepository;

    public void save(Amenity amenity) {
        amenityRepository.save(amenity);
    }

    @Transactional(readOnly = true)
    public List<Amenity> findAll() {
        return amenityRepository.findAll();
    }

    public Amenity findById(Long id) {
        return amenityRepository.findById(id);
    }

    public void deleteById(Long id) {
        amenityRepository.deleteById(id);
    }
}
