package com.clubtur.Parser;

import org.openqa.selenium.WebDriver;

import java.util.List;

public interface ModelParsable {
    List parse(WebDriver webDriver);
}
