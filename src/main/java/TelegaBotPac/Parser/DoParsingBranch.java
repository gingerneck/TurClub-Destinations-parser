package TelegaBotPac.Parser;

import TelegaBotPac.core.model.Route;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class DoParsingBranch {

    public static List<Route> getParsed(WebDriver webDriver) {

        List<Route> routes = new ArrayList<>();
        try {
            Thread.sleep(3000);
            while (true) {
                webDriver.findElement(new By.ByXPath("//*[@id=\"__layout\"]/div/div[2]/section/div/button")).click();
                Thread.sleep(6000);
            }
        } catch (NoSuchElementException | ElementClickInterceptedException | InterruptedException ex) {
            System.out.println("Errors in push Button");
        }
        try {
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
                    });
            System.out.println("Got ROUTES  - " + routes.size());
        }catch(Exception e){
            System.out.println("Errors to get info");
        }
        return routes;
    }
}
