package TelegaBotPac.Parser;

import TelegaBotPac.Connection;
import TelegaBotPac.core.model.Destination;
import TelegaBotPac.core.model.Route;
import TelegaBotPac.service.DriverServiceChrome;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * @return
     */
    public List<String> getTitle() {
        return getParsedUrl().stream().map(Destination::getNameDestination).collect(Collectors.toList());
    }

    public HashMap<Destination, List<Route>> getInfoRoute() throws IOException {

        HashMap<Destination, List<Route>> resMap = new HashMap<>();

        WebDriver driver = driverServiceChrome.getDriver(HOME_SEARCH_URL);
        try {
            List<Destination> destinations = DoParsingMain.getDestination(driver);

            System.out.println(destinations.size());

            for (Destination destination : destinations) {
                driver.get(destination.getLink());
                System.out.println(destination);
                try {
                    resMap.put(destination, DoParsingBranch.getParsed(driver));
                } catch (InterruptedException e) {
                    System.out.println("INTERRUPTED!!!!");
                    e.printStackTrace();
                }
            }
        }finally {
            driver.close();
        }
        return resMap;
    }

}
