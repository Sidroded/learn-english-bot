package com.sidroded.learnenglishbot.bot.commands;

import com.sidroded.learnenglishbot.bot.constant.MessageText;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartCommand {

    public SendMessage getMessage(String chatId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(String.format(MessageText.START_MESSAGE_TEXT, name));
        return message;
    }
}
