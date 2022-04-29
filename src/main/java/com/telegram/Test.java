package com.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
