package com.sidroded.learnenglishbot.bot.commands;

import com.sidroded.learnenglishbot.bot.constant.ButtonId;
import com.sidroded.learnenglishbot.bot.constant.ButtonText;
import com.sidroded.learnenglishbot.bot.constant.MessageText;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class AddWordCommand {

    public SendMessage getStartMessage(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(MessageText.ADD_MESSAGE_START_TEXT);
        return message;
    }

    public SendMessage getConfirmMessage(String chatId, String inputData) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(String.format(MessageText.ADD_MESSAGE_CONFIRM_TEXT, inputData));
        message.setReplyMarkup(confirmKeyboard());
        return message;
    }

    private InlineKeyboardMarkup confirmKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton YES_BUTTON = new InlineKeyboardButton();
        YES_BUTTON.setCallbackData(ButtonId.CONFIRM_BUTTON_YES);
        YES_BUTTON.setText(ButtonText.ADD_TEXT_CONFIRM_BUTTON);
        row.add(YES_BUTTON);

        InlineKeyboardButton NO_BUTTON = new InlineKeyboardButton();
        YES_BUTTON.setCallbackData(ButtonId.CONFIRM_BUTTON_NO);
        YES_BUTTON.setText(ButtonText.ADD_TEXT_CANCEL_BUTTON);
        row.add(NO_BUTTON);

        rows.add(row);
        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }
}
