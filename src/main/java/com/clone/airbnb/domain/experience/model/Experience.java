package com.clone.airbnb.domain.experience.model;

import com.clone.airbnb.common.model.TimestampModel;
import com.clone.airbnb.domain.experience.form.ExperienceForm;
import com.clone.airbnb.domain.experience.dto.ExperienceDto;
import com.clone.airbnb.domain.users.model.User;
import com.clone.airbnb.model.WishlistExperience;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "EXPERIENCE_ID"))
public class Experience extends TimestampModel {

    @Column(length = 250, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String country;

    @Column(length = 80, nullable = false)
    private String city;

    @Column(nullable = false)
    private Integer price;

    @Column(length = 250, nullable = false)
    private String address;

    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Temporal(TemporalType.TIME)
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "HOST_ID")
    private User host;

    @OneToMany(mappedBy = "experience")
    private List<ExperiencePerk> experiencePerks = new ArrayList<>();

    @OneToMany(mappedBy = "experience")
    private List<WishlistExperience> wishlistExperiences = new ArrayList<>();

    public Experience(ExperienceForm experienceForm, User user) {
        name = experienceForm.getName();
        country = experienceForm.getCountry();
        city = experienceForm.getCity();
        price = experienceForm.getPrice();
        address = experienceForm.getAddress();
        host = user;
    }

    public Experience(ExperienceDto experienceDto) {
        name = experienceDto.getName();
        country = experienceDto.getCountry();
        city = experienceDto.getCity();
        price = experienceDto.getPrice();
        address = experienceDto.getAddress();
        startTime = experienceDto.getStartTime();
    }

    public void setHost(User host) {
        this.host = host;
        host.getExperiences().add(this);
    }

    public static Experience createExperience(ExperienceDto experienceDto, User host) {
        Experience experience = new Experience(experienceDto);
        experience.setHost(host);
        return experience;
    }
}
