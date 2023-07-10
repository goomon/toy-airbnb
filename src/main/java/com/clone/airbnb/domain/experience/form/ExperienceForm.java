package com.clone.airbnb.domain.experience.form;

import com.clone.airbnb.domain.experience.model.Experience;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ExperienceForm {

    private Long id;

    private String name;

    private String country;

    private String city;

    private Integer price;

    private String address;

    private Long hostId;

    public ExperienceForm(Experience experience) {
        id = experience.getId();
        name = experience.getName();
        country = experience.getCountry();
        city = experience.getCity();
        price = experience.getPrice();
        address = experience.getAddress();
        hostId = experience.getHost().getId();
    }
}
