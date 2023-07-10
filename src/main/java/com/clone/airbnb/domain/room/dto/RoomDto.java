package com.clone.airbnb.domain.room.dto;

import com.clone.airbnb.domain.room.model.Room;
import com.clone.airbnb.domain.room.model.RoomKind;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class RoomDto {

    private Long id;

    private Date created;

    private Date modified;

    private String country;

    private String city;

    private Integer price;

    private Integer rooms;

    private Integer toilets;

    private String description;

    private String address;

    private Boolean petFriendly;

    private RoomKind roomKind;

    private Long owner;

    private List<Long> amenities;

    public RoomDto(Room room) {
        id = room.getId();
        created = room.getCreated();
        modified = room.getModified();
        country = room.getCountry();
        city = room.getCity();
        price = room.getPrice();
        rooms = room.getRooms();
        toilets = room.getToilets();
        description = room.getDescription();
        address = room.getAddress();
        petFriendly = room.getPetFriendly();
        roomKind = room.getRoomKind();
        owner = room.getOwner().getId();
        amenities = room.getRoomAmenities().stream()
                .map(x -> x.getAmenity().getId())
                .collect(Collectors.toList());
    }
}
