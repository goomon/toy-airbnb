package com.clone.airbnb.init;

import com.clone.airbnb.domain.contents.model.Room;
import com.clone.airbnb.domain.contents.model.RoomKind;
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
            Instant now = Instant.now();
            for (int i = 0; i < NUM; i++) {
                Room room = new Room();
                room.setCreated(Date.from(now));
                room.setModified(Date.from(now));
                Country country = faker.country();
                room.setCreated(Date.from(now));
                room.setModified(Date.from(now));
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
                em.persist(room);
            }
        }

        private static <T extends Enum<?>> T getRandomEnumValue(Class<T> clazz) {
            Random random = new Random();
            T[] enumValues = clazz.getEnumConstants();
            return enumValues[random.nextInt(enumValues.length)];
        }
    }
}
