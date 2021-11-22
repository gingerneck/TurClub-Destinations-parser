package TelegaBotPac;

import TelegaBotPac.core.cache.CacheManager;
import TelegaBotPac.core.model.Route;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
                new Thread(CacheManager::init).start();
            }

            if (CacheManager.isInit()) {
                if(CacheManager.getInstance().contains(message.getChatId())){
                    System.out.printf("%s %s%n",
                            message.getChatId(), CacheManager.getInstance().get(message.getChatId()));
                }

                int i = 1;
                if (CacheManager.getInstance().contains(messageout)) {
                    StringBuilder strb = new StringBuilder();
                    List<Route> routes = (List<Route>) CacheManager.getInstance().get(messageout);
                    for (Route route : routes) {
                        strb.append(route.getDescriptionForMessage(i++));
                        if(strb.length()>3500){
                            message.setText(strb.toString());
                            execute(message);
                            strb = new StringBuilder();
                        }
                    }
                    message.setText(strb.toString());
                    menu = false;
                } else {
                    StringBuilder strb = new StringBuilder();

                    for (String title : CacheManager.getKeys()) {
                        if(!((List)CacheManager.getInstance().get(title)).isEmpty()) {
                            strb.append(i++).append(". ").append(title).append("\n");
                        }
                    }
                    message.setText(strb.toString());
                    menu = false;
                }
            }else{
                message.setText("Data initialization");
            }

/*            if (menu) {
                message.setText(Menu.getMenu());
            }*/
            try {
                System.out.printf("ChatId: %s, Message: %s%n",message.getChatId(), messageout);
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
