package com.clone.airbnb.domain.room.repository;

import com.clone.airbnb.domain.room.model.RoomAmenity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoomAmenityRepository {

    private final EntityManager em;

    public void save(RoomAmenity roomAmenity) {
        em.persist(roomAmenity);
    }
}
