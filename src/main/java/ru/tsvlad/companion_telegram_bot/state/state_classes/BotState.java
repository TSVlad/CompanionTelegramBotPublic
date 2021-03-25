package ru.tsvlad.companion_telegram_bot.state.state_classes;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tsvlad.companion_telegram_bot.state.State;
import ru.tsvlad.companion_telegram_bot.state.StateAndMethodEntry;

public interface BotState {
    StateAndMethodEntry handleUpdate(Update update);
    State getState();
}
