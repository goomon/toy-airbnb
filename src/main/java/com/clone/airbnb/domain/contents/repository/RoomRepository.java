package com.clone.airbnb.domain.contents.repository;

import com.clone.airbnb.domain.contents.dto.RoomDto;
import com.clone.airbnb.domain.contents.model.Amenity;
import com.clone.airbnb.domain.contents.model.Room;
import com.clone.airbnb.domain.contents.model.RoomAmenity;
import com.clone.airbnb.domain.users.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomRepository {

    private final EntityManager em;

    public void save(Room room) {
        Instant now = Instant.now();
        room.setCreated(Date.from(now));
        room.setModified(Date.from(now));
        em.persist(room);
    }

    public void save(RoomDto roomDto) {
        Instant now = Instant.now();

        User owner = em.find(User.class, roomDto.getOwner());
        List<Amenity> amenities = em
                .createQuery("select a from Amenity a where a.id in :amenities", Amenity.class)
                .setParameter("amenities", roomDto.getAmenities())
                .getResultList();

        Room room = new Room(roomDto);
        room.setOwner(owner);
        room.setCreated(Date.from(now));
        room.setModified(Date.from(now));
        em.persist(room);

        for (Amenity amenity : amenities) {
            RoomAmenity roomAmenity = new RoomAmenity();
            roomAmenity.setRoom(room);
            roomAmenity.setAmenity(amenity);
            em.persist(roomAmenity);
        }
    }

    public List<Room> findAll() {
        return em.createQuery("select r from Room r", Room.class)
                .getResultList();
    }

    public Room findById(Long id) {
        return em.find(Room.class, id);
    }
}
