package com.arthrex.website.ui.pages.common;

import com.arthrex.website.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CookieDisclaimerPage extends BasePage {
    @FindBy(xpath = "//span[text()='Save Settings']")
    private WebElement btnSaveSettings;

    public CookieDisclaimerPage(WebDriver driver) {
        super(driver);
    }

    public void clickSaveSettings() {
        this.click(this.btnSaveSettings);
    }
}
