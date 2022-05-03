package com.telegram;

import com.telegram.core.cache.CacheManager;
import com.telegram.core.model.Route;
import com.telegram.service.BotService;
import com.telegram.service.DestinationService;
import com.telegram.service.RouteService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static com.telegram.Constants.CACHE_DESTINATION;

@Component
@Slf4j
public class TurClubRouter extends TelegramLongPollingBot {

    private final BotService botService;
    private final RouteService routeService;
    private final DestinationService destinationService;

    public TurClubRouter(BotService botService, RouteService routeService, DestinationService destinationService) {
        this.botService = botService;
        this.routeService = routeService;
        this.destinationService = destinationService;
    }

    @SneakyThrows
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageout = update.getMessage().getText();
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.enableHtml(true);
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            boolean menu = true;

            if ("s8s87vEStKSbaf7".equals(messageout)) {
                new Thread(botService::parseAllClub).start();
            }

            int i = 1;
            if (!CacheManager.isInit()) {
                botService.initCache();
            }
            List<String> destinations = (List) CacheManager.getInstance().get(CACHE_DESTINATION);
            if (destinations != null && destinations.contains(messageout)) {
                StringBuilder strb = new StringBuilder();
                for (Route route : routeService.findByDestination(messageout)) {
                    strb.append(route.getDescriptionForMessage(i++));
                    if (strb.length() > 3500) {
                        message.setText(strb.toString());
                        execute(message);
                        strb = new StringBuilder();
                    }
                }
                message.setText(strb.toString());
            } else if (destinations != null) {
                StringBuilder strb = new StringBuilder();
                for (String title : destinations) {
                    strb.append(i++).append(". ").append(title).append("\n");
                }
                message.setText(strb.toString());
            } else {
                message.setText("Try again");
            }

            try {
                System.out.printf("ChatId: %s, Message: %s%n", message.getChatId(), messageout);
                if (!message.getText().isEmpty()) {
                    execute(message);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "TurClubRouter";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "1907921546:AAE_wpmIGt1xdkG3B6npXFI_rpsv5EI9wiM";
    }
}
