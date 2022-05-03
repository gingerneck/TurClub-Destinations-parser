package com.telegram.service;

import com.telegram.Parser.ClubParsable;
import com.telegram.core.cache.CacheManager;
import com.telegram.core.dto.DestinationDTO;
import com.telegram.core.model.Company;
import com.telegram.core.model.Destination;
import com.telegram.core.model.Route;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.telegram.Constants.CACHE_DESTINATION;

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
        deleteAll();
        clubParsables.forEach(club -> {
            Map<Destination, List<Route>> data = club.parse();
            Company company = new Company();
            company.setName(club.getName());
            companyService.save(company);

            data.keySet().forEach(destination -> {
                destination.setCompany(company);
                destinationService.save(destination);
            });

            data.forEach((destination, routes) ->
                    routes.forEach(route -> routeService.save(route, destination))
            );
        });
        initCache();
    }

    public void initCache() {
        List<DestinationDTO> destinations = (List<DestinationDTO>) destinationService.findAll();
        CacheManager.init()
                .put(CACHE_DESTINATION,
                        destinations.stream()
                                .map(DestinationDTO::getName)
                                .collect(Collectors.toList()));

    }

    public void deleteAll() {
        companyService.deleteAll();
        routeService.deleteAll();
        destinationService.deleteAll();
    }
}
