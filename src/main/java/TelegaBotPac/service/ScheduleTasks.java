package TelegaBotPac.service;

import TelegaBotPac.core.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTasks {

    @Scheduled(cron="0 0 2 * * 1,3,6")
    public void initData(){
        if (CacheManager.isInit()) {
            CacheManager.getInstance().clear();
        }
        CacheManager.init();
    }
}
