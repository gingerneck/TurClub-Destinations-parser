package com.telegram.service;

import com.telegram.Parser.ParsePIK;
import com.telegram.core.model.Destination;
import com.telegram.core.model.Route;
import com.telegram.data.RouteRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @SneakyThrows
    public void setRoutesToDb(){
        ParsePIK parsePIK = new ParsePIK();
        Map<Destination, List<Route>> routes = parsePIK.getInfoRoute();

        routes.forEach(((destination, routesList) -> {
            routeRepository.saveAll(routesList);
        }));
    }

    public List<Route> getRoutes(){
        return (List<Route>) routeRepository.findAll();
    }

}
