package ru.tsvlad.companion_telegram_bot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class AnecdoteServiceImpl implements AnecdoteService {
    private String url = "https://www.anekdot.ru/random/anekdot/";

    @Override
    public String getRandomJoke() {
        return getAnecdoteFromHTML();
    }

    private String getAnecdoteFromHTML() {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Element element = doc.selectFirst("div.topicbox > div.text");
            return element.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Кажется, в моей оперативной памяти произошла ошибка, из-за которой я забыл все анекдоты. Не беспокойтесь, скоро меня починят. Надеюсь...";
    }
}
