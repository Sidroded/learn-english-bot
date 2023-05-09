package com.sidroded.learnenglishbot.database.service;

import com.sidroded.learnenglishbot.database.entity.User;
import com.sidroded.learnenglishbot.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(String chatId, String name) {
        User user = new User(chatId, name, false);
        if (userRepository.existsById(chatId))
            userRepository.save(user);
    }

    public Optional<User> getUser(String chatId) {
        return userRepository.findById(chatId);
    }
}
