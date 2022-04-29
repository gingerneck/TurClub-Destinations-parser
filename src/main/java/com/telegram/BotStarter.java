package com.telegram;

import com.telegram.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class BotStarter {

    private final SimpleBot bot;
    private final RouteService routeService;

    public BotStarter(SimpleBot bot, RouteService routeService) {
        this.bot = bot;
        this.routeService = routeService;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
/*            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }*/

      //  routeService.setRoutesToDb();

    }
}
