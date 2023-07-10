package com.clone.airbnb.domain.experience.model;

import com.clone.airbnb.domain.experience.dto.PerkDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Perk {

    @Id @GeneratedValue
    @Column(name = "PERK_ID")
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 150)
    private String details;

    @Column(length = 150)
    private String description;

    @OneToMany(mappedBy = "perk")
    private List<ExperiencePerk> experiencePerks = new ArrayList<>();

    public Perk(PerkDto perkDto) {
        name = perkDto.getName();
        details = perkDto.getDetails();
        description = perkDto.getDescription();
    }
}
