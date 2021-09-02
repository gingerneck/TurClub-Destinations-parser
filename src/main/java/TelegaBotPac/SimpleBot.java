package TelegaBotPac;

import TelegaBotPac.Parser.ParsePIK;
import TelegaBotPac.core.cache.CacheManager;
import TelegaBotPac.core.model.Route;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@Slf4j
public class SimpleBot extends TelegramLongPollingBot {

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
                if (CacheManager.isInit()) {
                    CacheManager.getInstance().clear();
                }
                CacheManager.init();
            }

            if (CacheManager.isInit()) {
                if(CacheManager.getInstance().contains(message.getChatId())){
                    System.out.println(String.format("%s %s",
                            message.getChatId(), CacheManager.getInstance().get(message.getChatId())));
                }

                int i = 1;
                if (CacheManager.getInstance().contains(messageout)) {
                    StringBuilder strb = new StringBuilder();
                    List<Route> routes = (List<Route>) CacheManager.getInstance().get(messageout);
                    for (Route route : routes) {
                        strb.append(i++).append(". ")
                                .append(String.format("<a href='%s'>", ParsePIK.HOME_URL+route.getLink()))
                                .append(route.getTitle())
                                .append("</a>")
                                .append("\n")
                                .append(route.getDescription() == null ? "" : (route.getDescription() + "\n"))
                                .append(String.format(" Стоимость: %s ", route.getCost() == null ?
                                        route.getAroundCost() == null ? "" : route.getAroundCost() : route.getCost()))
                                .append(route.getCurrency() == null ? "" : route.getCurrency())
                                .append("\n");
                        ;
                    }
                    message.setText(strb.toString());
                    menu = false;
                } else {
                    StringBuilder strb = new StringBuilder();
                    for (String title : CacheManager.getKeys()) {
                        strb.append(i++).append(". ").append(title).append("\n");
                    }
                    message.setText(strb.toString());
                    menu = false;
                }


            } else {
                CacheManager.init();
            }

/*            if (menu) {
                message.setText(Menu.getMenu());
            }*/
            try {
                if (!message.getText().isEmpty()) {
                    System.out.println(String.format("ChatId: %s, Message: %s",message.getChatId(), messageout));
               //     CacheManager.getInstance().put(message.getChatId(), messageout);
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
