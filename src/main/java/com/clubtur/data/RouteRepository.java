package com.clubtur.data;

import com.clubtur.core.model.Route;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<Route, Long> {

    Iterable<Route> findByDestinationId(Long id);
}
