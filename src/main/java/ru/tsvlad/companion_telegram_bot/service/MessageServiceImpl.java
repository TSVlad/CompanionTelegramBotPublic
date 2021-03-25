package ru.tsvlad.companion_telegram_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@PropertySource("application.properties")
public class MessageServiceImpl implements MessageService {
    private MessageSource messageSource;
    private Locale locale;

    @Autowired
    public MessageServiceImpl(@Value("${localeTag}") String localeTag, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(localeTag);
    }

    @Override
    public String getMessage(String messageCode) {
        return messageSource.getMessage(messageCode, null, locale);
    }
}
