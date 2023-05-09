package com.sidroded.learnenglishbot.bot.commands;

import com.sidroded.learnenglishbot.bot.constant.KeyboardText;
import com.sidroded.learnenglishbot.bot.constant.MessageText;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class StartCommand {

    public SendMessage getMessage(String chatId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(String.format(MessageText.START_MESSAGE_TEXT, name));
        message.setReplyMarkup(keyboard());
        return message;
    }

    private ReplyKeyboardMarkup keyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(KeyboardText.START_KEYBOARD_ADD_NEW_WORD);
        rows.add(row);

        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }
}
