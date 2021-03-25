package ru.tsvlad.companion_telegram_bot.service;

public interface NewsService {
    String getSportNews();
    String getTechnologiesNews();
    String getScienceNews();
    String getEconomicNews();
    String getPoliticsNews();
    String getMainNews();
}
