package ru.tsvlad.companion_telegram_bot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QuoteServiceImpl implements QuoteService {
    private String url = "https://quote-citation.com/random";

    @Override
    public String getRandomQoute() {
        return getQouteFromHTML();
    }

    private String getQouteFromHTML() {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            StringBuilder stringBuilder = new StringBuilder();
            Element qoute = doc.selectFirst("div.quote > div.quote-text > p");
            stringBuilder.append(qoute.text());
            stringBuilder.append("\n\n");
            Elements from = doc.select("div.quote > div.quote-text > p.source > a");
            for (Element element : from) {
                stringBuilder.append(element.text());
                stringBuilder.append(", ");
            }
            return stringBuilder.substring(0, stringBuilder.length() - 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Кажется, в моей оперативной памяти произошла ошибка, из-за которой я забыл все цитаты. Не беспокойтесь, скоро меня починят. Надеюсь...";
    }
}
