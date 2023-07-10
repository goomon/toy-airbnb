package com.clone.airbnb.domain.experience.repository;

import com.clone.airbnb.domain.experience.model.ExperiencePerk;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExperiencePerkRepository {

    private final EntityManager em;

    public void save(ExperiencePerk experiencePerk) {
        em.persist(experiencePerk);
    }

    public void delete(ExperiencePerk experiencePerk) {
        em.remove(experiencePerk);
    }
}