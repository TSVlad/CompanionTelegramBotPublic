package ru.tsvlad.companion_telegram_bot.update_handler;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tsvlad.companion_telegram_bot.state.StateAndMethodEntry;

public interface CommandHandler {
    StateAndMethodEntry handleCommand(Message message);
}
