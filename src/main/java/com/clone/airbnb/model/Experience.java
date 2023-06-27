package com.clone.airbnb.model;

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
    private Date start;

    @Temporal(TemporalType.TIME)
    private Date end;

    @ManyToOne
    @JoinColumn(name = "HOST_ID")
    private User host;

    @OneToMany(mappedBy = "experience")
    List<ExperiencePerk> experiencePerks = new ArrayList<>();
}
