package com.telegram.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTasks {

    private final RouteService routeService;

    public ScheduleTasks(RouteService routeService) {
        this.routeService = routeService;
    }

    @Scheduled(cron="0 0 2 * * 1,3,6")
    public void initData(){
        routeService.setRoutesToDb();
    }
}
