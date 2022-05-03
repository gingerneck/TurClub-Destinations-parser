package com.telegram.controller;

import com.telegram.core.dto.DestinationDTO;
import com.telegram.service.DestinationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/destinations")
    public List<DestinationDTO> findDestinations() {
        return (List<DestinationDTO>) destinationService.findAll();
    }
}
