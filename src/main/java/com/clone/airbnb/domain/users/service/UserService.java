package com.clone.airbnb.domain.users.service;

import com.clone.airbnb.domain.users.form.UserForm;
import com.clone.airbnb.domain.users.model.User;
import com.clone.airbnb.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public void update(Long id, UserForm userForm) {
        User user = userRepository.findById(id);
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setName(userForm.getName());
        user.setGender(userForm.getGender());
        user.setCurrency(userForm.getCurrency());
        user.setLanguage(userForm.getLanguage());
        user.setIsHost(userForm.getIsHost());
    }
}
