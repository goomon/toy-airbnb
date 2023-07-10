package com.clone.airbnb.init;

import com.clone.airbnb.domain.contents.model.*;
import com.clone.airbnb.domain.room.model.Amenity;
import com.clone.airbnb.domain.room.model.Room;
import com.clone.airbnb.domain.room.model.RoomAmenity;
import com.clone.airbnb.domain.room.model.RoomKind;
import com.clone.airbnb.domain.users.model.Currency;
import com.clone.airbnb.domain.users.model.Gender;
import com.clone.airbnb.domain.users.model.Language;
import com.clone.airbnb.domain.users.model.User;
import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class MockGenerator {

    private final Generator generator;

    @PostConstruct
    void start() {
        generator.init();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class Generator {

        private final EntityManager em;
        private final int NUM = 10;

        public void init() {
            Faker faker = new Faker();
            List<User> users = new ArrayList<>();
            for (int i = 0; i < NUM; i++) {
                User user = new User();
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setName(firstName + " " + lastName);
                user.setGender(getRandomEnumValue(Gender.class));
                user.setCurrency(getRandomEnumValue(Currency.class));
                user.setLanguage(getRandomEnumValue(Language.class));
                user.setIsHost(faker.bool().bool());
                users.add(user);
                em.persist(user);
            }

            Random random = new Random();
            List<Room> rooms = new ArrayList<>();
            Instant now = Instant.now();
            for (int i = 0; i < NUM; i++) {
                Room room = new Room();
                room.setCreated(Date.from(now));
                room.setModified(Date.from(now));
                Country country = faker.country();
                room.setCountry(country.name());
                room.setCity(country.capital());
                room.setPrice(faker.number().numberBetween(20, 100) * 1000);
                room.setRooms(faker.number().numberBetween(1, 3));
                room.setToilets(faker.number().numberBetween(1, 2));
                room.setDescription(faker.book().title());
                room.setAddress(faker.address().streetAddress());
                room.setRoomKind(getRandomEnumValue(RoomKind.class));
                room.setOwner(users.get(random.nextInt(users.size())));
                room.setPetFriendly(faker.bool().bool());
                rooms.add(room);
                em.persist(room);
            }
            for (int i = 0; i < NUM; i++) {
                Experience experience = new Experience();
                experience.setCreated(Date.from(now));
                experience.setModified(Date.from(now));
                experience.setName(faker.slackEmoji().activity());
                Country country = faker.country();
                experience.setCountry(country.name());
                experience.setCity(country.capital());
                experience.setPrice(faker.number().numberBetween(20, 100) * 1000);
                experience.setAddress(faker.address().streetAddress());
                experience.setHost(users.get(random.nextInt(users.size())));
                em.persist(experience);
            }

            List<Amenity> amenities = new ArrayList<>();
            for (int i = 0; i < NUM; i++) {
                Amenity amenity = new Amenity();
                amenity.setCreated(Date.from(now));
                amenity.setModified(Date.from(now));
                amenity.setName(faker.food().fruit().trim());
                amenity.setDescription(faker.weather().description());
                amenities.add(amenity);
                em.persist(amenity);
            }

            for (Room room : rooms) {
                for (int i = 0; i < 3; i++) {
                    Amenity amenity = amenities.get(random.nextInt(amenities.size()));
                    RoomAmenity roomAmenity = new RoomAmenity();
                    roomAmenity.setRoom(room);
                    roomAmenity.setAmenity(amenity);
                    em.persist(roomAmenity);
                }
            }
        }

        private static <T extends Enum<?>> T getRandomEnumValue(Class<T> clazz) {
            Random random = new Random();
            T[] enumValues = clazz.getEnumConstants();
            return enumValues[random.nextInt(enumValues.length)];
        }
    }
}