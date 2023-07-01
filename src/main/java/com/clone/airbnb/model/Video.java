package com.clone.airbnb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Video {

    @Id @GeneratedValue
    @Column(name = "VIDEO_ID")
    private Long id;

    @Column(nullable = false)
    private String file;

    @Column(length = 140)
    private String description;

    @ManyToOne
    @JoinColumn(name = "EXPERIENCE_ID")
    private Experience experience;
}
