package com.clubtur.data;

import com.clubtur.core.model.Destination;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DestinationRepository extends CrudRepository<Destination, Long> {

    public Optional<Destination> findByName(String name);
}
