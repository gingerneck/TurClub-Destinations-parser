package com.clubtur.parser.pik;

import com.clubtur.parser.ClubParsable;
import com.clubtur.utils.core.cache.CacheManager;
import com.clubtur.destination.Destination;
import com.clubtur.route.Route;
import com.clubtur.service.DriverServiceChrome;
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
           //     break;  //for tests
            }
        } finally {
            driver.close();
        }
        return resMap;
    }


}
