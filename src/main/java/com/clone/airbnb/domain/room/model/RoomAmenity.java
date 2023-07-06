package com.clone.airbnb.domain.room.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class RoomAmenity {

    @Id @GeneratedValue
    @Column(name = "ROOM_AMENITY_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "AMENITY_ID")
    private Amenity amenity;

    public void setRoom(Room room) {
        this.room = room;
        room.getRoomAmenities().add(this);
    }

    public void setAmenity(Amenity amenity) {
        this.amenity = amenity;
        amenity.getRoomAmenities().add(this);
    }
}
