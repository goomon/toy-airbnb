package com.clone.airbnb.domain.contents.service;

import com.clone.airbnb.domain.contents.form.ExperienceForm;
import com.clone.airbnb.domain.contents.model.Experience;
import com.clone.airbnb.domain.contents.repository.ExperienceRepository;
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
    private final UserRepository userRepository;

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
