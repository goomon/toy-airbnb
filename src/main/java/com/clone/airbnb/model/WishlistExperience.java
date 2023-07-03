package com.clone.airbnb.model;

import com.clone.airbnb.domain.contents.model.Experience;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class WishlistExperience {

    @Id @GeneratedValue
    @Column(name = "WISHLIST_EXPERIENCE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "WISHLIST_ID")
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "EXPERIENCE_ID")
    private Experience experience;
}
