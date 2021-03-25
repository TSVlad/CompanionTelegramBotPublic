package ru.tsvlad.companion_telegram_bot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NewsServiceImpl implements NewsService {
    private String urlTechnologies = "https://yandex.ru/news/rubric/computers";
    private String urlSport = "https://yandex.ru/sport";
    private String urlScience = "https://yandex.ru/news/rubric/science";
    private String urlEconomic = "https://yandex.ru/news/rubric/business";
    private String urlPolitic = "https://yandex.ru/news/rubric/politics";
    private String urlMain = "https://yandex.ru/news";

    @Override
    public String getSportNews() {
        return getNewsFromHTML(3, urlSport, "article.card.card_clickable-main-node", "div.card__body > a", "div.card__body > a > h2.card__title", "div.card__body > a > div.card__text");
    }

    @Override
    public String getTechnologiesNews() {
        return getNewsFromHTML(3, urlTechnologies, "article", "a", "a > h2", "div.mg-card__annotation");
    }

    @Override
    public String getScienceNews() {
        return getNewsFromHTML(3, urlScience, "article", "a", "a > h2", "div.mg-card__annotation");
    }

    @Override
    public String getEconomicNews() {
        return getNewsFromHTML(3, urlEconomic, "article", "a", "a > h2", "div.mg-card__annotation");
    }

    @Override
    public String getPoliticsNews() {
        return getNewsFromHTML(3, urlPolitic, "article", "a", "a > h2", "div.mg-card__annotation");
    }

    @Override
    public String getMainNews() {
        return getNewsFromHTML(3, urlMain, "article", "a", "a > h2", "div.mg-card__annotation");
    }

    private String getNewsFromHTML(int num, String url, String articleSelector, String aSelector, String headerSelector, String annotationSelector) {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements elements = doc.select(articleSelector);
            StringBuilder stringBuilder = new StringBuilder("Новости:\n\n");

            num = elements.size() > num ? num : elements.size();

            for (int i = 0; i < num; i++) {
                Element a = elements.get(i).selectFirst(aSelector);
                Element header = elements.get(i).selectFirst(headerSelector);
                Element annotation = elements.get(i).selectFirst(annotationSelector);

                stringBuilder.append(escapeText(header.text()));
                stringBuilder.append('\n');
                stringBuilder.append(escapeText(annotation.text()));
                stringBuilder.append('\n');
                stringBuilder.append("[Подробнее](");
                stringBuilder.append(a.attr("href"));
                stringBuilder.append(")");
                stringBuilder.append("\n\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Кажется, в моей оперативной памяти произошла ошибка, из-за которой я забыл все новости. Не беспокойтесь, скоро меня починят. Надеюсь...";
    }

    private String escapeText(String text) {
        return text.replaceAll("\\.", "\\\\.")
                .replaceAll("-", "\\\\-")
                .replaceAll("_", "\\\\_")
                .replaceAll("\\*", "\\\\*")
                .replaceAll("!", "\\\\!")
                .replaceAll("\\+", "\\\\+")
                .replaceAll("\\{", "\\\\{")
                .replaceAll("\\[", "\\\\[")
                .replaceAll("\\(", "\\\\(");
    }
}
