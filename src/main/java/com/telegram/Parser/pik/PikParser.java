package com.telegram.Parser.pik;

import com.telegram.Parser.ClubParsable;
import com.telegram.core.cache.CacheManager;
import com.telegram.core.model.Destination;
import com.telegram.core.model.Route;
import com.telegram.service.DriverServiceChrome;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class PikParser implements ClubParsable {
    public static final String HOME_URL = "https://turclub-pik.ru";
    public static final String HOME_SEARCH_URL = HOME_URL + "/search/";
    public static final String NAME = "ПИК";
    private final DriverServiceChrome driverServiceChrome;
    private final RoutesParser routesParser;
    private final DestinationParser destinationParser;

    public PikParser() {
        this.driverServiceChrome = new DriverServiceChrome();
        this.routesParser = new RoutesParser();
        this.destinationParser = new DestinationParser();
    }

    @Override
    public String getName() {
        return NAME;
    }

    public HashMap<Destination, List<Route>> parse() {
        HashMap<Destination, List<Route>> resMap = new HashMap<>();

        ChromeDriver driver = driverServiceChrome.getDriver(HOME_SEARCH_URL);
        try {
            List<Destination> destinations = destinationParser.parse(driver);
            System.out.println(destinations.size());
            for (Destination destination : destinations) {
                System.out.println(destination.toString());
                driver.get(destination.getLink());
                CacheManager.getInstance().put(getName() + "destination", destination.getName());
                resMap.put(destination, routesParser.parse(driver));
            }
        } finally {
            driver.close();
        }
        return resMap;
    }


}
