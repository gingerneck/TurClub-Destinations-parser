package com.telegram.service;

import com.telegram.core.model.Destination;
import com.telegram.data.DestinationRepository;

import java.util.List;

public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public void save(Destination destination){
        destinationRepository.save(destination);
    }

    public void saveAll(List<Destination> destinations){
        destinationRepository.saveAll(destinations);
    }
}
