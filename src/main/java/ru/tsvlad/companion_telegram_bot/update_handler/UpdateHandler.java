package ru.tsvlad.companion_telegram_bot.update_handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {
    BotApiMethod<?> handleUpdate(Update update);
}
