package com.clone.airbnb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ExperiencePerk {

    @Id @GeneratedValue
    @Column(name = "EXPERIENCE_PERK_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EXPERIENCE_ID")
    private Experience experience;

    @ManyToOne
    @JoinColumn(name = "PERK_ID")
    private Perk perk;
}
