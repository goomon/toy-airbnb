package com.clone.airbnb.domain.contents.repository;

import com.clone.airbnb.domain.contents.model.Amenity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AmenityRepository {

    private final EntityManager em;

    public void save(Amenity amenity) {
        Instant now = Instant.now();
        amenity.setCreated(Date.from(now));
        amenity.setModified(Date.from(now));
        em.persist(amenity);
    }

    public List<Amenity> findAll() {
        return em.createQuery("select a from Amenity a", Amenity.class)
                .getResultList();
    }

    public Amenity findById(Long id) {
        return em.find(Amenity.class, id);
    }

    public void deleteById(Long id) {
        Amenity amenity = em.find(Amenity.class, id);
        if (amenity != null) {
            em.remove(amenity);
        }
    }
}
