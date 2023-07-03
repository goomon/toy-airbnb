package com.clone.airbnb.model;

import com.clone.airbnb.domain.contents.model.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class WishlistRoom {

    @Id @GeneratedValue
    @Column(name = "WISHLIST_EXPERIENCE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "WISHLIST_ID")
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;
}
