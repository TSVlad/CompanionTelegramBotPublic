package ru.tsvlad.companion_telegram_bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tsvlad.companion_telegram_bot.update_handler.UpdateHandler;


@Component
@PropertySource("application.properties")
public class Bot extends TelegramWebhookBot {
//    private String username;
//    private String token;
//    private String path;
    @Autowired
    private UpdateHandler updateHandler;

    @Value("${bot.username}")
    private String username;
    @Value("${bot.token}")
    private String token;
    @Value("${bot.path}")
    private String path;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return updateHandler.handleUpdate(update);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotPath() {
        return path;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UpdateHandler getUpdateHandler() {
        return updateHandler;
    }

    public void setUpdateHandler(UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
    }
}
