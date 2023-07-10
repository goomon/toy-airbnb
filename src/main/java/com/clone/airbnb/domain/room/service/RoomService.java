package com.clone.airbnb.domain.room.service;

import com.clone.airbnb.domain.room.dto.RoomDto;
import com.clone.airbnb.domain.room.form.RoomForm;
import com.clone.airbnb.domain.room.model.Amenity;
import com.clone.airbnb.domain.room.model.Room;
import com.clone.airbnb.domain.room.model.RoomAmenity;
import com.clone.airbnb.domain.room.repository.AmenityRepository;
import com.clone.airbnb.domain.room.repository.RoomAmenityRepository;
import com.clone.airbnb.domain.room.repository.RoomRepository;
import com.clone.airbnb.domain.users.model.User;
import com.clone.airbnb.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final AmenityRepository amenityRepository;
    private final RoomAmenityRepository roomAmenityRepository;
    private final UserRepository userRepository;

    public void save(Room room) {
        roomRepository.save(room);
    }

    public void save(RoomDto roomDto) {
        User owner = userRepository.findById(roomDto.getOwner());
        List<Amenity> amenities = amenityRepository.findByIds(roomDto.getAmenities());
        Room room = Room.createRoom(roomDto, owner);
        for (Amenity amenity : amenities) {
            RoomAmenity roomAmenity = RoomAmenity.createRoomAmenity(room, amenity);
            roomAmenityRepository.save(roomAmenity);
        }
        roomRepository.save(room);
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

    public void deleteById(Long id) {
        Room room = roomRepository.findById(id);
        List<RoomAmenity> roomAmenities = room.getRoomAmenities();
        for (RoomAmenity roomAmenity : roomAmenities) {
            roomAmenityRepository.delete(roomAmenity);
        }
        roomRepository.delete(room);
    }
}
