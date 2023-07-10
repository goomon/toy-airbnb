package com.clone.airbnb.domain.room.repository;

import com.clone.airbnb.domain.room.model.Room;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomRepository {

    private final EntityManager em;

    public void save(Room room) {
        em.persist(room);
    }

    public List<Room> findAll() {
        return em.createQuery("select r from Room r", Room.class)
                .getResultList();
    }

    public Room findById(Long id) {
        return em.find(Room.class, id);
    }

    public void delete(Room room) {
        em.remove(room);
    }
}
