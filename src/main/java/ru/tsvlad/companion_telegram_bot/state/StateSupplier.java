package ru.tsvlad.companion_telegram_bot.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsvlad.companion_telegram_bot.state.state_classes.BotState;
import ru.tsvlad.companion_telegram_bot.state.state_classes.FreeState;
import ru.tsvlad.companion_telegram_bot.state.state_classes.NewsState;

@Component
public class StateSupplier {
    private FreeState freeState;
    private NewsState newsState;

    public BotState getState(State state) {
        switch (state) {
            case FREE_BOT_STATE:
                return freeState;
            case NEWS_BOT_STATE:
                return newsState;
            default:
                throw new RuntimeException("Unexpected bot state: " + state);
        }
    }

    @Autowired
    public void setFreeState(FreeState freeState, NewsState newsState) {
        this.freeState = freeState;
        this.newsState = newsState;
    }
}
