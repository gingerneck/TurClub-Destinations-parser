package com.clubtur.route;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public List<RouteDTO> getRoutes(){
        return (List<RouteDTO>) routeService.findAll();
    }

    @GetMapping("/routes-with-destination")
    public List<Route> getRoutes(@RequestParam String destination){
        return (List<Route>) routeService.findByDestination(destination);
    }
}
