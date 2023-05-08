package com.sidroded.learnenglishbot.bot;

import com.sidroded.learnenglishbot.bot.commands.StartCommand;
import com.sidroded.learnenglishbot.bot.config.BotConfig;
import com.sidroded.learnenglishbot.database.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;
    private final ConnectionService connectionService;

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String userName = update.getMessage().getChat().getFirstName();
        String text = update.getMessage().getText();

        switch (text) {
            case "/start": startCommandReceived(chatId, userName);
        }
    }

    private void startCommandReceived(String chatId, String name) {
        StartCommand startCommand = new StartCommand();
        connectionService.addUser(chatId, name);

        try {
            execute(startCommand.getMessage(chatId, name));
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
