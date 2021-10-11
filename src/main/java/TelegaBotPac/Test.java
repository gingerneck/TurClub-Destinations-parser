package TelegaBotPac;

import TelegaBotPac.Parser.ParsePIK;
import TelegaBotPac.core.cache.CacheManager;
import TelegaBotPac.core.model.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@Slf4j
public class Test {

/*    @EventListener({ContextRefreshedEvent.class})
    public void init() {

        if (CacheManager.isInit()) {
            CacheManager.getInstance().clear();
        }
        CacheManager.init();

        List<Route> routes = (List<Route>) CacheManager.getInstance().getFirstValue();
        for (Route route : routes) {
            System.out.println(route.toString());
        }
    }*/
}
