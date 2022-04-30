package com.telegram.service;

import com.telegram.Parser.ClubParsable;
import com.telegram.core.cache.CacheManager;
import com.telegram.core.model.Destination;
import com.telegram.core.model.Route;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.telegram.Constants.CACHE_DESTINATION;

@Component
public class BotService{
    private final RouteService routeService;
    private final DestinationService destinationService;
    private final List<ClubParsable> clubParsables;


    public BotService(RouteService routeService,
                      DestinationService destinationService,
                      List<ClubParsable> clubParsables) {
        this.routeService = routeService;
        this.destinationService = destinationService;
        this.clubParsables = clubParsables;
    }

    public void parseAllClub(){
        deleteAll();
        clubParsables.forEach(club->{
            Map<Destination, List<Route>> data = club.parse();
            destinationService.saveAll(data.keySet());
            routeService.saveAll(data.values()
                    .stream().flatMap(List::stream)
                    .collect(Collectors.toList()));
        });
        initCache();
    }

    public void initCache(){
        List<Destination> destinations = (List<Destination>) destinationService.findAll();
        List<String> ds = destinations.stream()
                .map(Destination::getName)
                .collect(Collectors.toList());
        CacheManager.init().put(CACHE_DESTINATION, ds);

    }

    public void deleteAll(){
        routeService.deleteAll();
        destinationService.deleteAll();
    }
}
