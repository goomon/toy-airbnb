package com.clone.airbnb.init;

import com.clone.airbnb.domain.experience.model.Experience;
import com.clone.airbnb.domain.experience.model.ExperiencePerk;
import com.clone.airbnb.domain.experience.model.Perk;
import com.clone.airbnb.domain.review.model.Review;
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
            for (int i = 0; i < NUM; i++) {
                Room room = new Room();
                Country country = faker.country();
                room.setCountry(country.name());
                room.setCity(country.capital());
                room.setPrice(faker.number().numberBetween(20, 100) * 1000);
                room.setRooms(faker.number().numberBetween(1, 3));
                room.setToilets(faker.number().numberBetween(1, 2));
                room.setDescription(faker.book().title());
                room.setAddress(faker.address().streetAddress());
                room.setRoomKind(getRandomEnumValue(RoomKind.class));
                room.setHost(users.get(random.nextInt(users.size())));
                room.setPetFriendly(faker.bool().bool());
                rooms.add(room);
                em.persist(room);
            }

            List<Experience> experiences = new ArrayList<>();
            for (int i = 0; i < NUM; i++) {
                Experience experience = new Experience();
                experience.setName(faker.slackEmoji().activity());
                Country country = faker.country();
                experience.setCountry(country.name());
                experience.setCity(country.capital());
                experience.setPrice(faker.number().numberBetween(20, 100) * 1000);
                experience.setAddress(faker.address().streetAddress());
                experience.setHost(users.get(random.nextInt(users.size())));
                experiences.add(experience);
                em.persist(experience);
            }

            List<Amenity> amenities = new ArrayList<>();
            for (int i = 0; i < NUM; i++) {
                Amenity amenity = new Amenity();
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

            List<Perk> perks = new ArrayList<>();
            for (int i = 0; i < NUM; i++) {
                Perk perk = new Perk();
                perk.setName(faker.food().fruit().trim());
                perk.setDetails(faker.weather().description());
                perk.setDescription(faker.weather().description());
                perks.add(perk);
                em.persist(perk);
            }

            for (Experience experience : experiences) {
                for (int i = 0; i < 3; i++) {
                    Perk perk = perks.get(random.nextInt(perks.size()));
                    ExperiencePerk experiencePerk = new ExperiencePerk();
                    experiencePerk.setExperience(experience);
                    experiencePerk.setPerk(perk);
                    em.persist(experiencePerk);
                }
            }

            for (int i = 0; i < NUM * 5; i++) {
                Review review = new Review();
                review.setUser(users.get(random.nextInt(users.size())));
                review.setExperience(experiences.get(random.nextInt(experiences.size())));
                review.setRating(random.nextInt(5) + 1);
                review.setPayload(faker.weather().description());
                em.persist(review);
            }
            for (int i = 0; i < NUM * 5; i++) {
                Review review = new Review();
                review.setUser(users.get(random.nextInt(users.size())));
                review.setRoom(rooms.get(random.nextInt(rooms.size())));
                review.setRating(random.nextInt(5) + 1);
                review.setPayload(faker.weather().description());
                em.persist(review);
            }
        }

        private static <T extends Enum<?>> T getRandomEnumValue(Class<T> clazz) {
            Random random = new Random();
            T[] enumValues = clazz.getEnumConstants();
            return enumValues[random.nextInt(enumValues.length)];
        }
    }
}
