package com.clone.airbnb.domain.experience.repository;

import com.clone.airbnb.domain.experience.model.Perk;
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

    public List<Perk> findByIds(List<Long> ids) {
        return em.createQuery("select p from Perk p where p.id in :ids", Perk.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    public void deleteById(Long id) {
        Perk perk = em.find(Perk.class, id);
        if (perk != null) {
            em.remove(perk);
        }
    }
}
