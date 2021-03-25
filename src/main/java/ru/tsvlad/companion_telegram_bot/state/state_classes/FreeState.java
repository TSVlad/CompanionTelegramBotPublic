package ru.tsvlad.companion_telegram_bot.state.state_classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tsvlad.companion_telegram_bot.service.*;
import ru.tsvlad.companion_telegram_bot.state.State;
import ru.tsvlad.companion_telegram_bot.state.StateAndMethodEntry;
import ru.tsvlad.companion_telegram_bot.update_handler.CommandHandler;

@Component
public class FreeState implements BotState{
    private State state = State.FREE_BOT_STATE;
    private ChatStateService chatStateService;
    private CommandHandler commandHandler;

    @Autowired
    public FreeState(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public StateAndMethodEntry handleUpdate(Update update) {
        StateAndMethodEntry reply = new StateAndMethodEntry(null, null);
        Message message = update.getMessage();
        if (message != null && message.hasText() && message.isCommand()) {
//            reply = handleInputMessage(message);
            reply = commandHandler.handleCommand(message);
        }
        return reply;
    }


    @Override
    public State getState() {
        return state;
    }

//    private StateAndMethodEntry handleInputMessage(Message message) {
//
//    }

    public ChatStateService getChatStateService() {
        return chatStateService;
    }

    //Чтобы не было цикла инъекций
    @Autowired
    public void setChatStateService(ChatStateService chatStateService) {
        this.chatStateService = chatStateService;
    }
}
