package com.sidroded.learnenglishbot.bot;

import com.sidroded.learnenglishbot.bot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

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
            case "/start": sendMessage("Hi", chatId);
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