package com.clone.airbnb.domain.users.form;

import com.clone.airbnb.domain.users.model.Currency;
import com.clone.airbnb.domain.users.model.Gender;
import com.clone.airbnb.domain.users.model.Language;
import com.clone.airbnb.domain.users.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserForm {

    private Long id;

    @NotBlank(message = "필수로 입력해야 합니다.")
    private String firstName;

    @NotBlank(message = "필수로 입력해야 합니다.")
    private String lastName;

    @NotBlank(message = "필수로 입력해야 합니다.")
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
