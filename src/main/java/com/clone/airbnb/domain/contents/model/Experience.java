package com.clone.airbnb.domain.contents.model;

import com.clone.airbnb.domain.contents.form.ExperienceForm;
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
public class Experience {

    @Id @GeneratedValue
    @Column(name = "EXPERIENCE_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

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

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

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
}
