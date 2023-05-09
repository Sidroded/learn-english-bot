package com.sidroded.learnenglishbot.bot.commands;

import com.sidroded.learnenglishbot.bot.constant.MessageText;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class AddWordCommand {

    public SendMessage getStartMessage(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(MessageText.ADD_MESSAGE_START_TEXT);
        return message;
    }
}
