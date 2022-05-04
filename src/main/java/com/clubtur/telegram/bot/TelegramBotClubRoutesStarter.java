package com.clubtur.telegram.bot;

import com.clubtur.service.BotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Slf4j
public class TelegramBotClubRoutesStarter {

    private final TelegramBotClubRoutes bot;
    private final BotService botService;

    public TelegramBotClubRoutesStarter(TelegramBotClubRoutes bot, BotService botService) {
        this.bot = bot;
        this.botService = botService;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
       // botService.parseAllClub();
    }
}
