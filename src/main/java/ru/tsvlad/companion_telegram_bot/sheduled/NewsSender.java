///*package ru.tsvlad.companion_telegram_bot.sheduled;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.meta.api.methods.ParseMode;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import ru.tsvlad.companion_telegram_bot.Bot;
//import ru.tsvlad.companion_telegram_bot.entity.ChatState;
//import ru.tsvlad.companion_telegram_bot.service.ChatStateService;
//import ru.tsvlad.companion_telegram_bot.service.NewsService;
//
//import java.util.List;
//
//@Component
//public class NewsSender {
//
//    private Bot bot;
//    private ChatStateService chatStateService;
//    private NewsService newsService;
//
//    @Autowired
//    public NewsSender(Bot bot, ChatStateService chatStateService, NewsService newsService) {
//        this.bot = bot;
//        this.chatStateService = chatStateService;
//        this.newsService = newsService;
//    }
//
//    @Scheduled(cron = "0 0 17 * * *")
//    public void sendNews() {
//        List<ChatState> chatStateList = chatStateService.getAllChatIDs();
//        String news = newsService.getBestDayNews(5);
//        try {
//            for (ChatState chatState : chatStateList) {
//                SendMessage sendMessage = new SendMessage(String.valueOf(chatState.getChatID()), news);
//                sendMessage.setParseMode(ParseMode.MARKDOWNV2);
//                sendMessage.disableWebPagePreview();
//                bot.execute(sendMessage);
//            }
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//}*/
