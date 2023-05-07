package com.sidroded.learnenglishbot.user;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class User {
    private String name;
    private String chatId;
    private final HashMap<String, String> words = new HashMap<>();

    public User(String chatId, String name) {
        this.name = name;
        this.chatId = chatId;
    }
}
