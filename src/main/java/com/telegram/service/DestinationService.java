package com.telegram.service;

import com.telegram.core.dto.DestinationDTO;
import com.telegram.core.model.Destination;
import com.telegram.data.DestinationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DestinationService {

    ModelMapper mapper = new ModelMapper();

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Transactional
    public void save(Destination destination){
        destinationRepository.save(destination);
    }

    @Transactional
    public void saveAll(Iterable<Destination> destinations){
        destinationRepository.saveAll(destinations);
    }

    @Transactional
    public void deleteAll(){
        destinationRepository.deleteAll();
    }

    @Transactional
    public Iterable<DestinationDTO> findAll() {
        List<Destination> destinations = (List<Destination>) destinationRepository.findAll();
        return destinations.stream()
                .map(destination -> {
                    DestinationDTO dto = mapper.map(destination, DestinationDTO.class);
                    dto.setDestinationCompany(destination.getCompany());
                    dto.setDestinationRoutes(destination.getRoutes());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Optional<Destination> findById(Long id){
        return destinationRepository.findById(id);
    }

    public Optional<Destination> findByName(String name){
        return destinationRepository.findByName(name);
    }
}
