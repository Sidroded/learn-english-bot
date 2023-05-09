package com.sidroded.learnenglishbot.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "name")
    private String name;

    @Column(name = "is_set_word")
    private Boolean isSetWord;
}