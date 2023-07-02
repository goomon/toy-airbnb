package com.clone.airbnb.model;

import com.clone.airbnb.domain.users.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Review {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "EXPERIENCE_ID")
    private Experience experience;

    @Lob
    private String payload;

    @Column(nullable = false)
    private Integer rating;
}
