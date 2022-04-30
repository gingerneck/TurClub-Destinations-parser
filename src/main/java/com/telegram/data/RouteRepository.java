package com.telegram.data;

import com.telegram.core.model.Route;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<Route, Long> {

    Iterable<Route> findByDestination(String destination);
}
