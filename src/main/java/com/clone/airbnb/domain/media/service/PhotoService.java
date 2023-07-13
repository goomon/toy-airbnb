package com.clone.airbnb.domain.media.service;

import com.clone.airbnb.domain.experience.model.Experience;
import com.clone.airbnb.domain.experience.repository.ExperienceRepository;
import com.clone.airbnb.domain.media.form.PhotoForm;
import com.clone.airbnb.domain.media.model.Photo;
import com.clone.airbnb.domain.media.repository.PhotoRepository;
import com.clone.airbnb.domain.room.model.Room;
import com.clone.airbnb.domain.room.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final RoomRepository roomRepository;
    private final ExperienceRepository experienceRepository;

    public void saveForm(PhotoForm photoForm) {
        Room room = null;
        Long roomId = photoForm.getRoomId();
        if (roomId != null) {
            room = roomRepository.findById(roomId);
        }

        Experience experience = null;
        Long experienceId = photoForm.getExperienceId();
        if (experienceId != null) {
            experience = experienceRepository.findById(experienceId);
        }

        Photo photo = Photo.create(photoForm, room, experience);
        photoRepository.save(photo);
    }

    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    public Photo findById(Long id) {
        return photoRepository.findById(id);
    }
}
