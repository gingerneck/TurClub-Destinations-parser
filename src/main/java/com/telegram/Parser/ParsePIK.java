package com.telegram.Parser;

import com.telegram.core.model.Destination;
import com.telegram.core.model.Route;
import com.telegram.service.DriverServiceChrome;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsePIK implements ClubParseble {

    DriverServiceChrome driverServiceChrome = new DriverServiceChrome();

    public static final String HOME_URL = "https://turclub-pik.ru";
    public static final String HOME_SEARCH_URL = HOME_URL + "/search/";

    /**
     * @return
     */
    public List<Destination> getParsedUrl() {
        return DoParsingMain.getDestination(
                driverServiceChrome.getDriver(HOME_SEARCH_URL));
    }

    public HashMap<Destination, List<Route>> getInfoRoute() throws IOException {

        HashMap<Destination, List<Route>> resMap = new HashMap<>();

        ChromeDriver driver = driverServiceChrome.getDriver(HOME_SEARCH_URL);
        try {
            List<Destination> destinations = DoParsingMain.getDestination(driver);

            System.out.println(destinations.size());

            for (Destination destination : destinations) {
                System.out.println(destination.toString());
                driver.get(destination.getLink());
                resMap.put(destination, DoParsingBranch.getParsed(driver));
            }
        } finally {
            driver.close();
        }
        return resMap;
    }

    public boolean isValidParsedData(Map<Destination, List<Route>> data) {
        int countRoutes = 0;
        for (Map.Entry<Destination, List<Route>> entry : data.entrySet()) {
            if (entry.getValue().isEmpty()) {
                countRoutes++;
            }
        }
        return countRoutes > (data.size() / 2);
    }

}
