package ru.tsvlad.companion_telegram_bot.state;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.tsvlad.companion_telegram_bot.state.state_classes.BotState;

public class StateAndMethodEntry {
    private BotApiMethod<?> method;
    private BotState state;

    public StateAndMethodEntry(BotApiMethod<?> method, BotState state) {
        this.method = method;
        this.state = state;
    }

    public BotApiMethod<?> getMethod() {
        return method;
    }

    public BotState getState() {
        return state;
    }
}
