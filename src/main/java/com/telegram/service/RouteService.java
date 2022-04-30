package com.telegram.service;

import com.telegram.core.model.Route;
import com.telegram.data.RouteRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @SneakyThrows
    public void saveAll(Iterable<Route> routes) {
        routeRepository.saveAll(routes);
    }

    public Iterable<Route> findAll() {
        return routeRepository.findAll();
    }

    public Iterable<Route> findByDestination(String destination) {
        return routeRepository.findByDestination(destination);
    }


    public void deleteAll(){
        routeRepository.deleteAll();
    }

}
