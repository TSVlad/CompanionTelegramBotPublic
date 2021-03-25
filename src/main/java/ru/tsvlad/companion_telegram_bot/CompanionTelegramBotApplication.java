package ru.tsvlad.companion_telegram_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CompanionTelegramBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanionTelegramBotApplication.class, args);
    }

}
