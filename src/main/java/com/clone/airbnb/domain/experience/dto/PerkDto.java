package com.clone.airbnb.domain.experience.dto;

import com.clone.airbnb.domain.experience.model.Perk;
import lombok.Data;

@Data
public class PerkDto {

    private Long id;

    private String name;

    private String details;

    private String description;

    public PerkDto(Perk perk) {
        id = perk.getId();
        name = perk.getName();
        details = perk.getDetails();
        description = perk.getDescription();
    }
}
