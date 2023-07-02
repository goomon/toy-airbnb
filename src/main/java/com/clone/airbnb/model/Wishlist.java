package com.clone.airbnb.model;

import com.clone.airbnb.domain.users.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Wishlist {

    @Id @GeneratedValue
    @Column(name = "REVIEW_ID")
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "wishlist")
    private List<WishlistRoom> wishlistRooms = new ArrayList<>();

    @OneToMany(mappedBy = "wishlist")
    private List<WishlistExperience> wishlistExperiences = new ArrayList<>();
}
