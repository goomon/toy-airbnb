package com.clone.airbnb.domain.review.repository;

import com.clone.airbnb.domain.review.model.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager em;

    public void save(Review review) {
        Instant now = Instant.now();
        review.setCreated(Date.from(now));
        em.persist(review);
    }

    public List<Review> findAll() {
        return em.createQuery("select r from Review r", Review.class)
                .getResultList();
    }

    public Review findById(Long id) {
        return em.find(Review.class, id);
    }

    public void delete(Review review) {
        em.remove(review);
    }

    public List<Review> paginate(Integer limit, Integer offset) {
        return em.createQuery("select r from Review r order by r.id desc", Review.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
