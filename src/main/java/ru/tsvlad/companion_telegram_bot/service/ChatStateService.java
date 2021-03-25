package ru.tsvlad.companion_telegram_bot.service;
import ru.tsvlad.companion_telegram_bot.entity.ChatState;
import ru.tsvlad.companion_telegram_bot.state.state_classes.BotState;

import java.util.List;

public interface ChatStateService {
    BotState getChatState(long chatID);
    void setChatState(ChatState chatState);
    List<ChatState> getAllChatIDs();
    void deleteChat(long chatID);
}
