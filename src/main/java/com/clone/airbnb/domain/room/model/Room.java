package com.clone.airbnb.domain.room.model;

import com.clone.airbnb.common.model.TimestampModel;
import com.clone.airbnb.domain.room.dto.RoomDto;
import com.clone.airbnb.domain.room.form.RoomForm;
import com.clone.airbnb.domain.users.model.User;
import com.clone.airbnb.model.WishlistRoom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "ROOM_ID"))
public class Room extends TimestampModel {

    @Column(length = 100, nullable = false)
    private String country;

    @Column(length = 100, nullable = false)
    private String city;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer rooms;

    @Column(nullable = false)
    private Integer toilets;

    @Lob
    private String description;

    @Column(length = 250, nullable = false)
    private String address;

    @Column(nullable = false)
    private Boolean petFriendly;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomKind roomKind;

    @ManyToOne
    @JoinColumn(name = "HOST_ID")
    private User host;

    @OneToMany(mappedBy = "room")
    private List<RoomAmenity> roomAmenities = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<WishlistRoom> wishlistRooms = new ArrayList<>();

    public Room(RoomForm roomForm, User user) {
        country = roomForm.getCountry();
        city = roomForm.getCity();
        price = roomForm.getPrice();
        rooms = roomForm.getRooms();
        toilets = roomForm.getToilets();
        description = roomForm.getDescription();
        address = roomForm.getAddress();
        petFriendly = roomForm.getPetFriendly();
        roomKind = roomForm.getRoomKind();
        host = user;
    }

    public Room(RoomDto roomDto) {
        country = roomDto.getCountry();
        city = roomDto.getCity();
        price = roomDto.getPrice();
        rooms = roomDto.getRooms();
        toilets = roomDto.getToilets();
        description = roomDto.getDescription();
        address = roomDto.getAddress();
        petFriendly = roomDto.getPetFriendly();
        roomKind = roomDto.getRoomKind();
    }

    public void setHost(User host) {
        this.host = host;
        host.getRooms().add(this);
    }

    public static Room createRoom(RoomDto roomDto, User host) {
        Room room = new Room(roomDto);
        room.setHost(host);
        return room;
    }
}
