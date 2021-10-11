package TelegaBotPac.Parser;

import TelegaBotPac.core.model.Destination;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class DoParsingMain {
    /**
     * @return HashMap<Integer, ArrayList < String>>
     */
    public static List<Destination> getDestination(WebDriver webDriver) {
        List<Destination> destinations = new ArrayList<>();
        webDriver.findElements(
                new By.ByXPath("//*[@id=\"__layout\"]/div/section/div/div[1]/div[2]/div[3]/div[1]/div"))
                .stream()
                .findFirst()
                .ifPresent(element ->
                    element.findElements(new By.ByCssSelector("a")).forEach(el -> {
                        if (!el.getAttribute("href").isEmpty()) {
                            destinations.add(new Destination.Builder()
                                    .setNameDestination(el.getAttribute("innerHTML").trim()
                                            .replaceAll("^[\n\r]", "").replaceAll("[\n\r]$", ""))
                                    .setLink(el.getAttribute("href"))
                                    .build());
                            System.out.println(el.getAttribute("innerHTML"));
                        }
                    })
                );
        return destinations;
    }
}
