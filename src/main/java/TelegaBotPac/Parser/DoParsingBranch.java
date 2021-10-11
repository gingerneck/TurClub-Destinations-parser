package TelegaBotPac.Parser;

import TelegaBotPac.core.model.Route;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class DoParsingBranch {

    /**
     * @param webDriver
     * @return
     */
    public static List<Route> getParsed(WebDriver webDriver) throws InterruptedException {

        List<Route> routes = new ArrayList<>();
        Thread.sleep(3000);
        try {
            while (true) {
                webDriver.findElement(new By.ByXPath("//*[@id=\"__layout\"]/div/div[2]/section/div/button")).click();
                Thread.sleep(6000);
            }
        } catch (NoSuchElementException | ElementClickInterceptedException ex) {
            System.out.println(ex.getMessage());
        }
        webDriver.findElements(new By.ByXPath("//*[@id=\"__layout\"]/div/div[2]/section/div/div[2]/div/div"))

                .forEach(obj -> {
                    Route.Builder routeBuilder = new Route.Builder();
                    routeBuilder.setCurrency(
                            obj.findElement(new By.ByCssSelector("span[itemprop=priceCurrency]")).getAttribute("content"));
                    routeBuilder.setLink(
                            obj.findElement(new By.ByCssSelector("a[itemprop=url]")).getAttribute("href"));
                    routeBuilder.setCost(
                            obj.findElement(new By.ByCssSelector("span[itemprop=price]")).getAttribute("content"));
                    routeBuilder.setTitle(
                            obj.findElement(new By.ByCssSelector("p[class=trip-card-title]")).getText());
                    routeBuilder.setDescription(
                            obj.findElement(new By.ByCssSelector("p[class=trip-card-description]")).getText());
                    routeBuilder.setAroundCost(
                            obj.findElement(new By.ByCssSelector("div[class=trip-card-price]")).getText());
                    routes.add(routeBuilder.build());
                    System.out.println(routeBuilder.build());
                });
        return routes;
    }
}
