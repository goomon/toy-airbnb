package com.clone.airbnb.domain.contents.model;

import com.clone.airbnb.domain.contents.model.Amenity;
import com.clone.airbnb.domain.contents.model.Room;
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
}
