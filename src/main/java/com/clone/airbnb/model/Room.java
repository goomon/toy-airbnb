package com.clone.airbnb.model;

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
public class Room {

    @Id @GeneratedValue
    @Column(name = "ROOM_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @Column(length = 50, nullable = false)
    private String country;

    @Column(length = 80, nullable = false)
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
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    @OneToMany(mappedBy = "room")
    private List<RoomAmenity> roomAmenities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
