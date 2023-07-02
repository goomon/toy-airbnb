package com.clone.airbnb.domain.users.form;

import com.clone.airbnb.domain.users.model.Currency;
import com.clone.airbnb.domain.users.model.Gender;
import com.clone.airbnb.domain.users.model.Language;
import com.clone.airbnb.domain.users.model.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserForm {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private String name;

    private Gender gender;

    private Language language;

    private Currency currency;

    private Boolean isHost;

    public UserForm(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        name = user.getName();
        gender = user.getGender();
        language = user.getLanguage();
        currency = user.getCurrency();
        isHost = user.getIsHost();
    }
}
