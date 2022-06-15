package com.clubtur.parser;

import com.clubtur.destination.Destination;
import com.clubtur.route.Route;

import java.util.List;
import java.util.Map;

public interface ClubParsable {
    String getName();
    Map<Destination, List<Route>> parse();
}
