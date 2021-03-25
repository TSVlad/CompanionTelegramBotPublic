package ru.tsvlad.companion_telegram_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsvlad.companion_telegram_bot.dao.ChatStateDAO;
import ru.tsvlad.companion_telegram_bot.entity.ChatState;
import ru.tsvlad.companion_telegram_bot.state.State;
import ru.tsvlad.companion_telegram_bot.state.StateSupplier;
import ru.tsvlad.companion_telegram_bot.state.state_classes.BotState;

import java.util.List;

@Transactional
@Service
public class ChatStateServiceImpl implements ChatStateService{

    private ChatStateDAO chatStateDAO;
    @Autowired
    private StateSupplier stateSupplier;

    @Autowired
    public ChatStateServiceImpl(ChatStateDAO chatStateDAO/*, *//*StateSupplier stateSupplier*/) {
        this.chatStateDAO = chatStateDAO;
//        this.stateSupplier = stateSupplier;
    }

    @Override
    public BotState getChatState(long chatID) {
        ChatState chatState = chatStateDAO.getState(chatID);
        if (chatState == null) {
            chatStateDAO.saveState(new ChatState(chatID, State.FREE_BOT_STATE));
            return stateSupplier.getState(State.FREE_BOT_STATE);
        }
        return stateSupplier.getState(chatState.getState());
    }

    @Override
    public void setChatState(ChatState chatState) {
        ChatState currentChatState = chatStateDAO.getState(chatState.getChatID());
        if (currentChatState != null) {
            currentChatState.setState(chatState.getState());
            chatStateDAO.saveState(currentChatState);
        } else {
            chatStateDAO.saveState(chatState);
        }
    }

    @Override
    public List<ChatState> getAllChatIDs() {
        return chatStateDAO.getAllRows();
    }

    @Override
    public void deleteChat(long chatID) {
        chatStateDAO.deleteRow(chatID);
    }

    public StateSupplier getStateSupplier() {
        return stateSupplier;
    }

    public void setStateSupplier(StateSupplier stateSupplier) {
        this.stateSupplier = stateSupplier;
    }
}
