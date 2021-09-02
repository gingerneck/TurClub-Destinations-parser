package TelegaBotPac.Parser;

import TelegaBotPac.core.model.Destination;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class DoParsingMain {
    /**
     * @param uc - Document - DOM SITE
     * @return HashMap<Integer, ArrayList < String>>
     */
    public static List<Destination> getDestination(Document uc) {
        List<Destination> destinations = new ArrayList<>();
        Elements links = uc.select("div");
        for (Element obj : links) {
            String name = obj.attr("class");
            if ("footer-regions".equals(name)) {
                Elements objLi = obj.select("a");
                for (Element objLis : objLi) {
                    if(!objLis.attr("href").isEmpty()) {
                        destinations.add(new Destination.Builder()
                                .setNameDestination(objLis.text())
                                .setLink(objLis.attr("href"))
                                .build());
                    }
                }
            }
        }
        return destinations;
    }
}
