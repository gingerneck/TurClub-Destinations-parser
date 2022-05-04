package com.clubtur.telegram.bot;

import com.clubtur.core.cache.CacheManager;
import com.clubtur.core.model.Route;
import com.clubtur.service.BotService;
import com.clubtur.service.DestinationService;
import com.clubtur.service.RouteService;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static com.clubtur.core.Constants.CACHE_DESTINATION;

@Component
@Slf4j
@ConfigurationProperties(prefix = "turclub.bot")
public class TelegramBotClubRoutes extends TelegramLongPollingBot {

    @Setter
    private String token;
    @Setter
    private String reset;
    private final BotService botService;
    private final RouteService routeService;
    private final DestinationService destinationService;

    public TelegramBotClubRoutes(BotService botService, RouteService routeService, DestinationService destinationService) {
        this.botService = botService;
        this.routeService = routeService;
        this.destinationService = destinationService;
    }

    @SneakyThrows
    public void onUpdateReceived(Update update) {
        String messageText = null;
        Long chatId = null;
        if (update.hasMessage() && update.getMessage().hasText()) {
            messageText = update.getMessage().getText();
            chatId = update.getMessage().getChatId();
        } else if (update.hasCallbackQuery() && update.getCallbackQuery().getMessage() != null) {
            messageText = update.getCallbackQuery().getData();
            chatId = update.getCallbackQuery().getMessage().getChatId();
        }
        String messageout = messageText;
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.enableHtml(true);
        message.setChatId(String.valueOf(chatId));
        boolean menu = true;

        if (!CacheManager.isInitilising() && reset.equals(messageout)) {
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

            message = sendInlineKeyBoardMessage(chatId, destinations);

               /* StringBuilder strb = new StringBuilder();
                for (String title : destinations) {
                    strb.append(i++).append(". ").append(title).append("\n");
                }
                message.setText(strb.toString());*/
        } else {
            message.setText("Try again");
        }

        try {
            System.out.printf("ChatId: %s, Message: %s%n", chatId, messageout);
            if (!message.getText().isEmpty()) {
                execute(message);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "TurClubRouter";
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @SneakyThrows
    public static SendMessage sendInlineKeyBoardMessage(long chatId, List<String> destinations) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        for (var dist:destinations) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(dist);
            inlineKeyboardButton.setCallbackData(dist);
            keyboardButtonsRow.add(inlineKeyboardButton);
            if(keyboardButtonsRow.size()==2
                    || destinations.size() % 2 != 0
                    && rowList.size()*2 == destinations.size() - 1 ){
                rowList.add(keyboardButtonsRow);
                keyboardButtonsRow = new ArrayList<>();
            }
        }

        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage message = new SendMessage(String.valueOf(chatId), "Выберите область");
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }
}
