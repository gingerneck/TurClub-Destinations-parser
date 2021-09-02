package TelegaBotPac.Parser;

import TelegaBotPac.Connection;
import TelegaBotPac.core.model.Destination;
import TelegaBotPac.core.model.Route;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ParsePIK implements ClubParseble {

    public static final String HOME_URL = "https://turclub-pik.ru";
    public static final String HOME_SEARCH_URL = HOME_URL + "/search/";

    /**
     * @return
     * @throws IOException
     */
    public static List<Destination> getParsedUrl() throws IOException {
        URL url = new URL(HOME_SEARCH_URL);
        Document uc = Connection.getDOMDocument(url);
        return DoParsingMain.getDestination(uc);
    }

    /**
     * @return
     * @throws IOException
     */
    public static List<String> getTitle() throws IOException {
        return getParsedUrl().stream().map(Destination::getNameDestination).collect(Collectors.toList());
    }

    public static HashMap<Destination, List<Route>> getInfoRoute() throws IOException {

        HashMap<Destination, List<Route>> resMap = new HashMap<>();
        Document uc = Connection.getDOMDocument(new URL(HOME_SEARCH_URL));

        List<Destination> destinations = DoParsingMain.getDestination(uc);

        System.out.println(destinations.size());

        for (Destination destination : destinations) {
            Document route = Connection.getDOMDocument(
                    new URL(String.format("%s%s", HOME_URL, destination.getLink())));
            if (route != null) {
                resMap.put(destination, DoParsingBranch.getParsed(route));
            }
        }

        return resMap;
    }

}
