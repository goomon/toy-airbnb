package com.clone.airbnb.domain.contents.dto;

import com.clone.airbnb.domain.contents.model.Amenity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AmenityDto {

    private Long id;

    private String name;

    private String description;

    public AmenityDto(Amenity amenity) {
        id = amenity.getId();
        name = amenity.getName();
        description = amenity.getDescription();
    }
}
