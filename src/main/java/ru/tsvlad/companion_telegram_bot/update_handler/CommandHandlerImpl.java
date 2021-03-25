package ru.tsvlad.companion_telegram_bot.update_handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.tsvlad.companion_telegram_bot.service.*;
import ru.tsvlad.companion_telegram_bot.state.State;
import ru.tsvlad.companion_telegram_bot.state.StateAndMethodEntry;
import ru.tsvlad.companion_telegram_bot.state.StateSupplier;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandHandlerImpl implements CommandHandler {

    private AnecdoteService anecdoteService;
    private QuoteService quoteService;
    private FactsService factsService;
    private MessageService messageService;
    private ChatStateService chatStateService;
    private StateSupplier stateSupplier;

    @Autowired
    public CommandHandlerImpl(AnecdoteService anecdoteService, QuoteService quoteService, FactsService factsService, MessageService messageService, ChatStateService chatStateService) {
        this.anecdoteService = anecdoteService;
        this.quoteService = quoteService;
        this.factsService = factsService;
        this.messageService = messageService;
        this.chatStateService = chatStateService;
    }

    @Override
    public StateAndMethodEntry handleCommand(Message message) {
        String messageText = message.getText().trim();
        switch (messageText) {
            case "/start":
            case "/start@CompanionTSBot":
            case "/start@testing_tsbot":
                return new StateAndMethodEntry(new SendMessage(message.getChatId().toString(), messageService.getMessage("reply.start") + message.getFrom().getFirstName()), stateSupplier.getState(State.FREE_BOT_STATE));
            case "/help":
            case "/help@CompanionTSBot":
            case "/help@testing_tsbot":
                return new StateAndMethodEntry(new SendMessage(message.getChatId().toString(), messageService.getMessage("reply.help")), stateSupplier.getState(State.FREE_BOT_STATE));
            case "/anecdote":
            case "/anecdote@CompanionTSBot":
            case "/anecdote@testing_tsbot":
                return new StateAndMethodEntry(new SendMessage(message.getChatId().toString(), anecdoteService.getRandomJoke()), stateSupplier.getState(State.FREE_BOT_STATE));
            case "/quote":
            case "/quote@CompanionTSBot":
            case "/quote@testing_tsbot":
                return new StateAndMethodEntry(new SendMessage(message.getChatId().toString(), quoteService.getRandomQoute()), stateSupplier.getState(State.FREE_BOT_STATE));
            case "/fact":
            case "/fact@CompanionTSBot":
            case "/fact@testing_tsbot":
                return new StateAndMethodEntry(new SendMessage(message.getChatId().toString(), factsService.getRandomFact()), stateSupplier.getState(State.FREE_BOT_STATE));
            case "/stop":
            case "/stop@CompanionTSBot":
            case "/stop@testing_tsbot":
                chatStateService.deleteChat(message.getChatId());
                return new StateAndMethodEntry(new SendMessage(message.getChatId().toString(), messageService.getMessage("reply.stop")), null);
            case "/news":
            case "/news@CompanionTSBot":
            case "/news@testing_tsbot":
                SendMessage sendMessage = new SendMessage(message.getChatId().toString(), messageService.getMessage("reply.news"));
                setNewsMarkup(sendMessage);
                return new StateAndMethodEntry(sendMessage, stateSupplier.getState(State.NEWS_BOT_STATE));
            default:
                return new StateAndMethodEntry(new SendMessage(message.getChatId().toString(), messageService.getMessage("reply.default")), stateSupplier.getState(State.FREE_BOT_STATE));
        }
    }

    @Autowired
    public void setStateSupplier(StateSupplier stateSupplier) {
        this.stateSupplier = stateSupplier;
    }

    private void setNewsMarkup(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setOneTimeKeyboard(true);

        KeyboardButton buttonMain = new KeyboardButton("Главное");
        KeyboardButton buttonTechnologies = new KeyboardButton("Технологии");
        KeyboardButton buttonSport = new KeyboardButton("Спорт");
        KeyboardButton buttonScience = new KeyboardButton("Наука");
        KeyboardButton buttonEconomics = new KeyboardButton("Экономика");
        KeyboardButton buttonPolitic = new KeyboardButton("Политика");

        KeyboardRow row1 = new KeyboardRow();
        row1.add(buttonMain);
        row1.add(buttonTechnologies);
        KeyboardRow row2 = new KeyboardRow();
        row2.add(buttonSport);
        row2.add(buttonScience);
        KeyboardRow row3 = new KeyboardRow();
        row3.add(buttonPolitic);
        row3.add(buttonEconomics);

        List<KeyboardRow> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        keyboardMarkup.setKeyboard(rowList);

        sendMessage.setReplyMarkup(keyboardMarkup);
    }
}
