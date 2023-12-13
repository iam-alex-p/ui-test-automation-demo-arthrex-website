package com.arthrex.website.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    private static final long TIMEOUT = 20L;
    private static final long POLLING = 30L;

    protected final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(TIMEOUT), Duration.ofSeconds(POLLING));
        this.actions = new Actions(this.driver);

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));

        PageFactory.initElements(this.driver, this);
    }

    public void waitForElementToAppear(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element) {
        this.waitForElementToBeClickable(element);
        element.click();
    }

    public void enterText(WebElement element, String text) {
        this.waitForElementToAppear(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public boolean isElementExists(By locator) {
        return this.driver.findElements(locator).size() > 0;
    }

    public boolean isElementHasAttr(WebElement element, String attrName) {
        return element.getAttribute(attrName) != null;
    }

    public void moveToElement(WebElement element) throws InterruptedException {
        // TODO fix Thread.sleep hard-coded delay
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    public Boolean isElementWithTextExists(String text) {
        return this.driver.findElements(By.xpath(String.format("//*[text()='%s']", text))).size() > 0;
    }

    public String getAttributeValue(WebElement element, String attribute) {
        if (this.isElementHasAttr(element, attribute))
            return element.getAttribute(attribute);

        return null;
    }
}
