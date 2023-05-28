package com.sidroded.learnenglishbot.bot;

import com.sidroded.learnenglishbot.bot.commands.AddWordCommand;
import com.sidroded.learnenglishbot.bot.commands.StartCommand;
import com.sidroded.learnenglishbot.bot.config.BotConfig;
import com.sidroded.learnenglishbot.bot.constant.KeyboardText;
import com.sidroded.learnenglishbot.database.service.UserService;
import com.sidroded.learnenglishbot.utils.DictionaryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final StartCommand startCommand = new StartCommand();
    private final AddWordCommand addWordCommand = new AddWordCommand();
    private final DictionaryUtils dictionaryUtils = new DictionaryUtils();


    @Autowired
    private UserService userService;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String userName = update.getMessage().getChat().getFirstName();
        String text = update.getMessage().getText();

        switch (text) {
            case "/start" -> startCommandReceived(chatId, userName);
            case KeyboardText.START_KEYBOARD_ADD_NEW_WORD -> addWordStartCommandReceived(chatId);
            default -> {
                if (userService.isAddWord(chatId)) {
                    addWordConfirmCommandReceived(chatId, text);
                }
            }
        }
    }

    private void startCommandReceived(String chatId, String name) {
        userService.addUser(chatId, name);

        try {
            execute(startCommand.getMessage(chatId, name));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void addWordStartCommandReceived(String chatId) {
        userService.setIsAddWord(chatId, true);

        try {
            execute(addWordCommand.getStartMessage(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void addWordConfirmCommandReceived(String chatId, String text) {
        try {
            if (dictionaryUtils.isWordTranslationPattern(text)) {
                execute(addWordCommand.getConfirmMessage(chatId, text));
            } else {
                execute(addWordCommand.getWrongInputDataMessage(chatId));
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(String text, String chatId) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
