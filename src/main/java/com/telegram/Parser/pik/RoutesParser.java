package com.telegram.Parser.pik;

import com.telegram.Parser.ModelParsable;
import com.telegram.core.model.Route;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RoutesParser implements ModelParsable {

    public List<Route> parse(WebDriver webDriver) {

        List<Route> routes = new ArrayList<>();
        try {
            Thread.sleep(3000);
            while (true) {
                webDriver.findElement(new By.ByXPath("//*[@id=\"__layout\"]/div/div[2]/section/div[1]/div/div[1]/button")).click();
                Thread.sleep(6000);
            }
        } catch (Throwable ex) {
            System.out.println("Errors in push Button. " + ex.getMessage());
        }

        List<WebElement> cardElements = webDriver
                .findElements(
                        new By.ByXPath(
                                "//*[@id=\"__layout\"]/div/div[2]/section/div[1]/div/div[1]/div[2]/div/div"));
        for (WebElement element : cardElements) {
            try {
                PriceCurrency priceCurrency = getPriceCurrency(element);
                routes.add(Route.builder()
                        .currency(priceCurrency.getCurrency())
                        .cost(priceCurrency.getPrice())
                        .link(element.findElement(new By.ByCssSelector("a[itemprop=url]")).getAttribute("href"))
                        .title(element.findElement(new By.ByCssSelector("p[class=trip-card-title]")).getText())
                        .description(element.findElement(new By.ByCssSelector("p[class=trip-card-description]")).getText())
                        .aroundCost(element.findElement(new By.ByCssSelector("div[class=trip-card-price]")).getText())
                        .build()
                );
                // for tests
           /*     if(routes.size()>4){
                    break;
                }*/
            } catch (Exception e) {
                System.out.println("Errors to get info. " + e.getMessage());
            }
        }
        System.out.println("Got ROUTES  - " + routes.size());
        return routes;
    }

    private static PriceCurrency getPriceCurrency(WebElement obj) {
        String currency = "";
        String price = "";
        try {
            currency = obj.findElement(new By.ByCssSelector("span[itemprop=priceCurrency]")).getAttribute("content");
            price = obj.findElement(new By.ByCssSelector("span[itemprop=price]")).getAttribute("content");
        } catch (Throwable e) {
            System.out.println("can not read span[itemprop=priceCurrency]");
            try {
                String priceText = obj.findElement(By.cssSelector("div.trip-card-price > span")).getText();
                String[] priceCurrency = priceText.split(" ");
                if (priceCurrency.length >= 2) {
                    currency = priceCurrency[priceCurrency.length-1];
                }
                price = priceText.replaceAll("\\D", "");
            } catch (Throwable t) {
                System.out.println("can not read div.trip-card-price");
            }
        }
        return new PriceCurrency(price, currency);
    }
}
