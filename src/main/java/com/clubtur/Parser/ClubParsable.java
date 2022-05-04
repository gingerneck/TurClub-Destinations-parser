package com.clubtur.Parser;

import com.clubtur.core.model.Destination;
import com.clubtur.core.model.Route;

import java.util.List;
import java.util.Map;

public interface ClubParsable {
    String getName();
    Map<Destination, List<Route>> parse();
}
