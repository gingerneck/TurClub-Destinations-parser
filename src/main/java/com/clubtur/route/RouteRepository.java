package com.clubtur.route;

import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<Route, Long> {

    Iterable<Route> findByDestinationId(Long id);
}
