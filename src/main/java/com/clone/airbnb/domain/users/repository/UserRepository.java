package com.clone.airbnb.domain.users.repository;

import com.clone.airbnb.domain.users.model.User;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u order by u.id", User.class)
                .getResultList();
    }

    public List<User> findHost() {
        return em.createQuery("select u from User u where u.isHost = true", User.class)
                .getResultList();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }
}
