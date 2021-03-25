package ru.tsvlad.companion_telegram_bot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FactsServiceImpl implements FactsService {

    private String url = "https://randstuff.ru/fact/";

    @Override
    public String getRandomFact() {
        return getFactFromHTML();
    }

    private String getFactFromHTML() {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Element element = doc.selectFirst("div#fact > table.text > tbody > tr > td");
            return element.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Кажется, в моей оперативной памяти произошла ошибка, из-за которой я забыл все факты. Не беспокойтесь, скоро меня починят. Надеюсь...";
    }
}
