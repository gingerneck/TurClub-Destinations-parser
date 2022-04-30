package com.telegram.service;

import com.telegram.core.model.Destination;
import com.telegram.data.DestinationRepository;
import org.springframework.stereotype.Service;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public void save(Destination destination){
        destinationRepository.save(destination);
    }

    public void saveAll(Iterable<Destination> destinations){
        destinationRepository.saveAll(destinations);
    }

    public void deleteAll(){
        destinationRepository.deleteAll();
    }

    public Iterable<Destination> findAll(){
        return destinationRepository.findAll();
    }
}
