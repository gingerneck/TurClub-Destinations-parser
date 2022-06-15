package com.clubtur.route;

import com.clubtur.destination.Destination;
import com.clubtur.destination.DestinationService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    ModelMapper mapper = new ModelMapper();

    private final RouteRepository routeRepository;
    private final DestinationService destinationService;

    public RouteService(RouteRepository routeRepository, DestinationService destinationService) {
        this.routeRepository = routeRepository;
        this.destinationService = destinationService;
    }

    @SneakyThrows
    @Transactional
    public void saveAll(Iterable<Route> routes) {
        routeRepository.saveAll(routes);
    }

    @SneakyThrows
    @Transactional
    public void save(Route route, Destination destination) {
        route.setDestination(destination);
        routeRepository.save(route);
    }

    public Iterable<RouteDTO> findAll() {
        List<Route> routes = (List<Route>) routeRepository.findAll();
        return routes.stream()
                .map(route -> {
                    RouteDTO dto = mapper.map(route, RouteDTO.class);
                    dto.setRouteDestination(route.getDestination());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Iterable<Route> findByDestination(String destination) {
        List<Route> routes = new ArrayList<>();
        destinationService.findByName(destination).ifPresent(dest->{
            routes.addAll(
                    (Collection<? extends Route>) routeRepository.findByDestinationId(dest.getId()));
        });
        return routes;
    }

    @Transactional
    public void deleteAll() {
        routeRepository.deleteAll();
    }

}
