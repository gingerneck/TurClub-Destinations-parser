package com.telegram.data;

import com.telegram.core.model.Destination;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DestinationRepository extends CrudRepository<Destination, Long> {

    public Optional<Destination> findByName(String name);
}
