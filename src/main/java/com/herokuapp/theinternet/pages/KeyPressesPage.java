package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class KeyPressesPage extends BasePageObject {
    //private String pageUrl = "http://the-internet.herokuapp.com/key_presses";
    private By keyBoxLocator = By.id("target");
    private By actualTextLocator = By.id("result");
    public KeyPressesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    public void pressKey(Keys key) {
        log.info("Pressing " + key.name());
        pressKey(keyBoxLocator, key);
    }

    public String getActualText(){
        String actualText = find(actualTextLocator).getText();
        log.info("Actual text: " + actualText);
        return actualText;
    }
}
