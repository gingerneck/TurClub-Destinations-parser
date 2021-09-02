package TelegaBotPac.Parser;

import TelegaBotPac.core.model.Route;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class DoParsingBranch {
    /**
     * @param uc - Document - DOM SITE
     * @return HashMap<Integer, ArrayList < String>>
     */
    public static List<Route> getParsed(Document uc) {

        List<Route> routes = new ArrayList<>();
        Integer i = 0;

        Elements links = uc.select("div");

        for (Element obj : links) {
            String name = obj.attr("class");
            if ("trip-card".equals(name)) {
                Route.Builder routeBuilder = new Route.Builder();
                Elements itemprop = obj.getElementsByAttribute("itemprop");
                itemprop.forEach(item ->{
                    if(item.text()!=null){

                        if(item.select("span[itemprop=price]").hasAttr("content")){
                            routeBuilder.setCost(item.select("span[itemprop=price]").attr("content"));
                        }
                        if(item.select("span[itemprop=priceCurrency]").hasAttr("content")){
                            routeBuilder.setCurrency(item.select("span[itemprop=priceCurrency]").attr("content"));
                        }
                        if(item.select("a[itemprop=url]").hasAttr("href")){
                            routeBuilder.setLink(item.select("a[itemprop=url]").attr("href"));
                        }
                        if(item.select("p[class=trip-card-title]").hasText()){
                            routeBuilder.setTitle(item.select("p[class=trip-card-title]").text());
                        }
                    }
                });
                if(obj.select("p[class=trip-card-description]").hasText()){
                    routeBuilder.setDescription(obj.select("p[class=trip-card-description]").text());
                }
                if(obj.select("div[class=trip-card-price]").hasText()){
                    routeBuilder.setAroundCost(obj.select("div[class=trip-card-price]").text());
                }
                routes.add(routeBuilder.build());
            }
        }

        return routes;
    }
}
