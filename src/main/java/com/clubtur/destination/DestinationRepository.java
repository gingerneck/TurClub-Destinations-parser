package com.clubtur.destination;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DestinationRepository extends CrudRepository<Destination, Long> {

    public Optional<Destination> findByName(String name);
}
