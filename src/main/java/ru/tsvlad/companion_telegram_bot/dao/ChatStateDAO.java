package ru.tsvlad.companion_telegram_bot.dao;

import ru.tsvlad.companion_telegram_bot.entity.ChatState;

import java.util.List;

public interface ChatStateDAO {
    ChatState getState(long chat_id);
    void saveState(ChatState state);
    List<ChatState> getAllRows();
    void deleteRow(long chatID);
}
