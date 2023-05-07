package com.sidroded.learnenglishbot.user;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class UserService {
    private final HashMap<String, User> users = new HashMap<>();

    public void addUser(String chatId, String name) {
        users.put(chatId, new User(chatId, name));
    }
}
