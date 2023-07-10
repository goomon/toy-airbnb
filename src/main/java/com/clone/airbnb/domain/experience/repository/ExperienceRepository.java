package com.clone.airbnb.domain.experience.repository;

import com.clone.airbnb.domain.experience.model.Experience;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExperienceRepository {

    private final EntityManager em;

    public void save(Experience experience) {
        em.persist(experience);
    }

    public List<Experience> findAll() {
        return em.createQuery("select e from Experience e", Experience.class)
                .getResultList();
    }

    public Experience findById(Long id) {
        return em.find(Experience.class, id);
    }

    public void delete(Experience experience) {
        em.remove(experience);
    }
}
