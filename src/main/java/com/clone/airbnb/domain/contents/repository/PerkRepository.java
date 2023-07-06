package com.clone.airbnb.domain.contents.repository;

import com.clone.airbnb.domain.contents.model.Perk;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PerkRepository {

    private final EntityManager em;

    public void save(Perk perk) {
        em.persist(perk);
    }

    public List<Perk> findAll() {
        return em.createQuery("select p from Perk p", Perk.class)
                .getResultList();
    }

    public Perk findById(Long id) {
        return em.find(Perk.class, id);
    }

    public void deleteById(Long id) {
        Perk perk = em.find(Perk.class, id);
        em.remove(perk);
    }
}
