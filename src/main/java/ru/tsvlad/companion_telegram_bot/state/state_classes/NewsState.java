package ru.tsvlad.companion_telegram_bot.state.state_classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import ru.tsvlad.companion_telegram_bot.service.MessageService;
import ru.tsvlad.companion_telegram_bot.service.NewsService;
import ru.tsvlad.companion_telegram_bot.state.State;
import ru.tsvlad.companion_telegram_bot.state.StateAndMethodEntry;
import ru.tsvlad.companion_telegram_bot.state.StateSupplier;
import ru.tsvlad.companion_telegram_bot.update_handler.CommandHandler;

@Component
public class NewsState implements BotState {
    private State state = State.NEWS_BOT_STATE;
    private NewsService newsService;
    private StateSupplier stateSupplier;
    private MessageService messageService;
    private CommandHandler commandHandler;

    @Autowired
    public NewsState(NewsService newsService, MessageService messageService, CommandHandler commandHandler) {
        this.newsService = newsService;
        this.messageService = messageService;
        this.commandHandler = commandHandler;
    }

    @Override
    public StateAndMethodEntry handleUpdate(Update update) {
        StateAndMethodEntry reply = new StateAndMethodEntry(null, null);
        Message message = update.getMessage();
        if (message != null && message.isCommand()) {
            reply = commandHandler.handleCommand(message);
        } else if (message != null && message.hasText()) {
            reply = handleMessage(update);
        }
        return reply;
    }

    private StateAndMethodEntry handleMessage(Update update) {
        String text = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setParseMode(ParseMode.MARKDOWNV2);
        sendMessage.disableWebPagePreview();
        State nextState = State.FREE_BOT_STATE;
        switch (text) {
            case "Главное":
                sendMessage.setText(newsService.getMainNews());
                break;
            case "Технологии":
                sendMessage.setText(newsService.getTechnologiesNews());
                break;
            case "Спорт":
                sendMessage.setText(newsService.getSportNews());
                break;
            case "Наука":
                sendMessage.setText(newsService.getScienceNews());
                break;
            case "Экономика":
                sendMessage.setText(newsService.getEconomicNews());
                break;
            case "Политика":
                sendMessage.setText(newsService.getPoliticsNews());
                break;
            default:
                sendMessage.setText(messageService.getMessage("reply.news.default"));
        }
        return new StateAndMethodEntry(sendMessage, stateSupplier.getState(nextState));
    }

    @Override
    public State getState() {
        return state;
    }

    @Autowired
    public void setStateSupplier(StateSupplier stateSupplier) {
        this.stateSupplier = stateSupplier;
    }
}
