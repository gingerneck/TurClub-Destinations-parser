package com.telegram.Parser;

import com.telegram.core.model.Destination;
import com.telegram.core.model.Route;

import java.util.List;
import java.util.Map;

public interface ClubParsable {
    String getName();
    Map<Destination, List<Route>> parse();
}
