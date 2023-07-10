package com.clone.airbnb.domain.experience.model;

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

    public static ExperiencePerk createExperiencePerk(Experience experience, Perk perk) {
        ExperiencePerk experiencePerk = new ExperiencePerk();
        experiencePerk.setExperience(experience);
        experiencePerk.setPerk(perk);
        return experiencePerk;
    }
}
