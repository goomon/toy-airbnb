package com.clone.airbnb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Booking {

    @Id @GeneratedValue
    @Column(name = "BOOKING_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryKind categoryKind;

    @ManyToOne
    @JoinColumn(name = "USER_ID ")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "EXPERIENCE_ID")
    private Experience experience;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date checkIn;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date checkOut;

    @Column(nullable = false)
    private Integer guests;
}
