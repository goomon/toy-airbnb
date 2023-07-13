package com.clone.airbnb.domain.media.repository;

import com.clone.airbnb.domain.media.model.Photo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PhotoRepository {

    private final EntityManager em;

    public void save(Photo photo) {
        em.persist(photo);
    }

    public List<Photo> findAll() {
        return em.createQuery("select p from Photo p", Photo.class)
                .getResultList();
    }

    public Photo findById(Long id) {
        return em.find(Photo.class, id);
    }
}
