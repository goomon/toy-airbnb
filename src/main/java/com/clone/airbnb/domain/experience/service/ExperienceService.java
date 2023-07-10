package com.clone.airbnb.domain.experience.service;

import com.clone.airbnb.domain.experience.form.ExperienceForm;
import com.clone.airbnb.domain.experience.dto.ExperienceDto;
import com.clone.airbnb.domain.experience.model.Experience;
import com.clone.airbnb.domain.experience.model.ExperiencePerk;
import com.clone.airbnb.domain.experience.model.Perk;
import com.clone.airbnb.domain.experience.repository.ExperiencePerkRepository;
import com.clone.airbnb.domain.experience.repository.ExperienceRepository;
import com.clone.airbnb.domain.experience.repository.PerkRepository;
import com.clone.airbnb.domain.users.model.User;
import com.clone.airbnb.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final PerkRepository perkRepository;
    private final ExperiencePerkRepository experiencePerkRepository;
    private final UserRepository userRepository;

    public void save(ExperienceDto experienceDto) {
        User host = userRepository.findById(experienceDto.getHost());
        List<Perk> perks = perkRepository.findByIds(experienceDto.getPerks());
        Experience experience = Experience.createExperience(experienceDto, host);
        for (Perk perk : perks) {
            ExperiencePerk experiencePerk = ExperiencePerk.createExperiencePerk(experience, perk);
            experiencePerkRepository.save(experiencePerk);
        }
        experienceRepository.save(experience);
    }

    public void save(ExperienceForm experienceForm) {
        Long hostId = experienceForm.getHostId();
        User host = userRepository.findById(hostId);
        Experience experience = new Experience(experienceForm, host);
        experienceRepository.save(experience);
    }

    @Transactional(readOnly = true)
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    public Experience findById(Long id) {
        return experienceRepository.findById(id);
    }

    public void update(Long id, ExperienceForm experienceForm) {
        Experience experience = experienceRepository.findById(id);
        Instant now = Instant.now();
        experience.setModified(Date.from(now));
        experience.setCountry(experienceForm.getCountry());
        experience.setName(experienceForm.getName());
        experience.setCity(experienceForm.getCity());
        experience.setPrice(experienceForm.getPrice());
        experience.setAddress(experienceForm.getAddress());
        User user = userRepository.findById(experienceForm.getId());
        experience.setHost(user);
    }
}
