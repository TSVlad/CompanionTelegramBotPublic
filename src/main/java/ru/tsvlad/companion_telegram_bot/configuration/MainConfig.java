package ru.tsvlad.companion_telegram_bot.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import ru.tsvlad.companion_telegram_bot.Bot;
import ru.tsvlad.companion_telegram_bot.update_handler.UpdateHandler;

@Configuration
//@ConfigurationProperties(prefix = "bot")
@PropertySource("application.properties")
public class MainConfig {
//    @Value("${bot.username}")
//    private String username;
//    @Value("${bot.token}")
//    private String token;
//    @Value("${bot.path}")
//    private String path;

//    private UpdateHandler updateHandler;

//    @Autowired
//    public MainConfig(UpdateHandler updateHandler) {
//        this.updateHandler = updateHandler;
//    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("classpath:bot_replies");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }

//    @Bean
//    public Bot bot(){
//        Bot bot = new Bot();
//        bot.setUsername(username);
//        bot.setToken(token);
//        bot.setPath(path);
//        bot.setUpdateHandler(updateHandler);
//
//        return bot;
//    }

}
