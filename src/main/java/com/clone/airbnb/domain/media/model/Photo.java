package com.clone.airbnb.domain.media.model;

import com.clone.airbnb.domain.experience.model.Experience;
import com.clone.airbnb.domain.media.form.PhotoForm;
import com.clone.airbnb.domain.room.model.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Photo {

    @Id @GeneratedValue
    @Column(name = "PHOTO_ID")
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(length = 140)
    private String description;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @OneToOne
    @JoinColumn(name = "EXPERIENCE_ID")
    private Experience experience;

    public static Photo create(PhotoForm photoForm, Room room, Experience experience) {
        Photo photo = new Photo();
        photo.setFilename(photoForm.getFile().getOriginalFilename());
        photo.setDescription(photoForm.getDescription());
        photo.setRoom(room);
        photo.setExperience(experience);
        return photo;
    }
}
