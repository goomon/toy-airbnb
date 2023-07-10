package com.clone.airbnb.domain.experience.service;

import com.clone.airbnb.domain.experience.model.Perk;
import com.clone.airbnb.domain.experience.repository.PerkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PerkService {

    private final PerkRepository perkRepository;

    public void save(Perk perk) {
        perkRepository.save(perk);
    }

    @Transactional(readOnly = true)
    public List<Perk> findAll() {
        return perkRepository.findAll();
    }

    public Perk findById(Long id) {
        return perkRepository.findById(id);
    }

    public void deleteById(Long id) {
        perkRepository.deleteById(id);
    }
}
