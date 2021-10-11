package TelegaBotPac.service;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class DriverServiceChrome {

    @SneakyThrows
    public WebDriver getDriver(String url){

        System.setProperty("webdriver.chrome.driver",  "/home/opc/chromedriver");
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("no-sandbox");
        opt.addArguments("disable-dev-shm-usage");
        opt.addArguments("headless");

        WebDriver driver = new ChromeDriver(opt);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(url);

        return driver;

    }
}
