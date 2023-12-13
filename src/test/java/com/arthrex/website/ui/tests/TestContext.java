package com.arthrex.website.ui.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestContext {
    private WebDriver driver;

    public void setupWebDriver() {
        WebDriverManager.chromedriver().driverVersion("").setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    public void teardownWebDriver() {
        if (this.driver != null)
            driver.quit();
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
