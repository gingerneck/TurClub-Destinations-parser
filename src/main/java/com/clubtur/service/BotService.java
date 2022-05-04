package com.clubtur.service;

import com.clubtur.Parser.ClubParsable;
import com.clubtur.core.cache.CacheManager;
import com.clubtur.core.dto.DestinationDTO;
import com.clubtur.core.model.Company;
import com.clubtur.core.model.Destination;
import com.clubtur.core.model.Route;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.clubtur.core.Constants.CACHE_DESTINATION;

@Component
public class BotService {
    private final RouteService routeService;
    private final DestinationService destinationService;
    private final CompanyService companyService;
    private final List<ClubParsable> clubParsables;


    public BotService(RouteService routeService,
                      DestinationService destinationService,
                      CompanyService companyService, List<ClubParsable> clubParsables) {
        this.routeService = routeService;
        this.destinationService = destinationService;
        this.companyService = companyService;
        this.clubParsables = clubParsables;
    }

    public void parseAllClub() {
        CacheManager.setInitilising(true);
        try {
            deleteAll();
            clubParsables.forEach(club -> {
                Map<Destination, List<Route>> data = club.parse();
                Company company = new Company();
                company.setName(club.getName());
                companyService.save(company);

                data.keySet().forEach(destination -> destination.setCompany(company));
                destinationService.saveAll(data.keySet());
                data.forEach((destination, routes) ->
                        routes.forEach(route -> routeService.save(route, destination))
                );
            });
            initCache();
        } finally {
            CacheManager.setInitilising(false);
        }
    }

    public void initCache() {
        List<DestinationDTO> destinations = (List<DestinationDTO>) destinationService.findAll();
        CacheManager.init()
                .put(CACHE_DESTINATION,
                        destinations.stream()
                                .filter(dest-> !dest.getDestinationRoutes().isEmpty())
                                .map(DestinationDTO::getName)
                                .collect(Collectors.toList()));

    }

    public void deleteAll() {
        companyService.deleteAll();
        routeService.deleteAll();
        destinationService.deleteAll();
    }
}
