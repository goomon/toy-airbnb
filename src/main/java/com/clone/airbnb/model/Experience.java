package com.clone.airbnb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Experience {

    @Id @GeneratedValue
    @Column(name = "EXPERIENCE_ID")
    private Long id;
}
