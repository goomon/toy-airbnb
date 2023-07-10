package com.clone.airbnb.domain.experience.dto;

import com.clone.airbnb.domain.experience.model.Experience;
import com.clone.airbnb.domain.users.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ExperienceDto {

    private Long id;

    private Date created;

    private Date modified;

    private String name;

    private String country;

    private String city;

    private Integer price;

    private String address;

    private Date startTime;

    private Date endTime;

    private Long host;

    List<Long> perks;

    public ExperienceDto(Experience experience) {
        id = experience.getId();
        created = experience.getCreated();
        modified = experience.getModified();
        name = experience.getName();
        country = experience.getCountry();
        city = experience.getCity();
        price = experience.getPrice();
        address = experience.getAddress();
        startTime = experience.getStartTime();
        endTime = experience.getEndTime();
        host = experience.getHost().getId();
        perks = experience.getExperiencePerks().stream()
                .map(x -> x.getPerk().getId())
                .collect(Collectors.toList());
    }
}
