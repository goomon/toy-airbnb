package com.clone.airbnb.domain.users.model;

import com.clone.airbnb.domain.contents.model.Experience;
import com.clone.airbnb.domain.room.model.Room;
import com.clone.airbnb.domain.users.form.UserForm;
import com.clone.airbnb.model.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "USERS")
public class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(length = 150, nullable = false)
    private String firstName;

    @Column(length = 150, nullable = false)
    private String lastName;

    private String avatar;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isHost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @OneToMany(mappedBy = "owner")
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "host")
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ChatRoomUser> chatRoomUsers = new ArrayList<>();


    public User(UserForm userForm) {
        firstName = userForm.getFirstName();
        lastName = userForm.getLastName();
        name = userForm.getName();
        isHost = userForm.getIsHost();
        gender = userForm.getGender();
        language = userForm.getLanguage();
        currency = userForm.getCurrency();
    }
}
