package com.arthrex.website.ui.pages.common;

import com.arthrex.website.ui.pages.BasePage;
import com.arthrex.website.ui.utilities.Consts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ToastMessagesPage extends BasePage {
    public ToastMessagesPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAlertSuccess() {
        return this.getWebElementOrNullBy(By.cssSelector(Consts.CSS_SELECTOR_ALERT_SUCCESS));
    }
}
