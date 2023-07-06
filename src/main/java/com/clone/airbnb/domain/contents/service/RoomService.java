package com.clone.airbnb.domain.contents.service;

import com.clone.airbnb.domain.contents.dto.RoomDto;
import com.clone.airbnb.domain.contents.form.RoomForm;
import com.clone.airbnb.domain.contents.model.Room;
import com.clone.airbnb.domain.contents.repository.RoomRepository;
import com.clone.airbnb.domain.users.model.User;
import com.clone.airbnb.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public void save(Room room) {
        roomRepository.save(room);
    }

    public void save(RoomDto roomDto) {
        roomRepository.save(roomDto);
    }

    public void save(RoomForm roomForm) {
        Long ownerId = roomForm.getOwnerId();
        User owner = userRepository.findById(ownerId);
        Room room = new Room(roomForm, owner);
        roomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(Long id) {
        return roomRepository.findById(id);
    }

    public void update(Long id, RoomForm roomForm) {
        Room room = roomRepository.findById(id);
        Instant now = Instant.now();
        room.setModified(Date.from(now));
        room.setCountry(roomForm.getCountry());
        room.setCity(roomForm.getCity());
        room.setPrice(roomForm.getPrice());
        room.setRooms(roomForm.getRooms());
        room.setToilets(roomForm.getToilets());
        room.setDescription(roomForm.getDescription());
        room.setAddress(roomForm.getAddress());
        room.setPetFriendly(roomForm.getPetFriendly());
        room.setRoomKind(roomForm.getRoomKind());
        User user = userRepository.findById(roomForm.getOwnerId());
        room.setOwner(user);
    }
}
