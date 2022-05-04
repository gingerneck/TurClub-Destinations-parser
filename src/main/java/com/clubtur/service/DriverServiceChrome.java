package com.clubtur.service;

import lombok.SneakyThrows;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DriverServiceChrome {
    public static final String OPC_DRIVER = "/home/opc/chromedriver";
    public static final String MAC_DRIVER = "chromedriver";

    @SneakyThrows
    public ChromeDriver getDriver(String url){
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("os.name", "generic")
                        .toLowerCase(Locale.ENGLISH).contains("nux")
                        ?OPC_DRIVER:MAC_DRIVER);
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("no-sandbox");
        opt.addArguments("disable-dev-shm-usage");
        opt.addArguments("headless");
        ChromeDriver driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        return driver;
    }
}
