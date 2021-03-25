package ru.tsvlad.companion_telegram_bot.update_handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tsvlad.companion_telegram_bot.entity.ChatState;
import ru.tsvlad.companion_telegram_bot.service.ChatStateService;
import ru.tsvlad.companion_telegram_bot.state.StateAndMethodEntry;
import ru.tsvlad.companion_telegram_bot.state.state_classes.BotState;

@Component
public class UpdateHandlerImpl implements UpdateHandler{

    private ChatStateService chatStateService;

    @Autowired
    public UpdateHandlerImpl( ChatStateService chatStateService) {
        this.chatStateService = chatStateService;
    }

    public BotApiMethod<?> handleUpdate(Update update) {
        if(update.getMessage() != null || update.getCallbackQuery() != null) {
            long chatID = -1;
            if (update.getCallbackQuery() != null) {
                chatID = update.getCallbackQuery().getMessage().getChatId();
            } else if(update.getMessage() != null) {
                chatID = update.getMessage().getChatId();
            }
            BotState botState = chatStateService.getChatState(chatID);
            StateAndMethodEntry stateAndMethodEntry = botState.handleUpdate(update);
            BotApiMethod<?> method = stateAndMethodEntry.getMethod();
            BotState nextState = stateAndMethodEntry.getState();
            if (nextState != null && botState.getState() != nextState.getState()) {
                chatStateService.setChatState(new ChatState(chatID, nextState.getState()));
            }
            return method;
        }
        return null;
    }
}
