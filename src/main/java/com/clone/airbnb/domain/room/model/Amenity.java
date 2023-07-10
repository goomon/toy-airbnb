package com.clone.airbnb.domain.room.model;

import com.clone.airbnb.domain.room.dto.AmenityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Amenity {

    @Id @GeneratedValue
    @Column(name = "AMENITY_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @Column(length = 50)
    private String name;

    @Column(length = 150)
    private String description;

    @OneToMany(mappedBy = "amenity")
    private List<RoomAmenity> roomAmenities = new ArrayList<>();

    public Amenity(AmenityDto amenityDto) {
        name = amenityDto.getName();
        description = amenityDto.getDescription();
    }
}
